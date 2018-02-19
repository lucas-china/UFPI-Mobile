package ufpi.br.ufpimobile;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ufpi.br.ufpimobile.controllers.TestConnection;
import ufpi.br.ufpimobile.model.CalendarioDAO;

public class CalendarioGrad extends AppCompatActivity {

    private static final String TAG = "TelaHome";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy ", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private Toolbar toolbar;
    private CompactCalendarView compactCalendarView;
    private boolean shouldShow = false;

    public RequestQueue queue;
    private List<CalendarioDAO> listCalendario;
    String url = "http://mobile.ufpi.br/api/calendars/5a74a95318e3ee48319a55ce?kind=grad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        final List<String> informacoes = new ArrayList<>();

        final ListView datasListView = (ListView) findViewById(R.id.datas_listview);
        final Button slideCalendarBut = (Button) findViewById(R.id.slide_calendar);

        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, informacoes);
        datasListView.setAdapter(adapter);

        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);


        //Alterar o texto da toolbar para a data selecionada
        toolbar = (Toolbar) findViewById(R.id.toolbar_Calendario);
        toolbar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), EscolherCalendario.class);
                finish();
                startActivity(home);
            }
        });

        if (new TestConnection(getApplicationContext()).isConnected()) {
            queue = Volley.newRequestQueue(this);
            fetchPosts();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Sem acesso a Internet!!", Toast.LENGTH_LONG);
            toast.show();

        }


        // Definir o título na rolagem do calendário
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                toolbar.setTitle(dateFormatForMonth.format(dateClicked));
                List<Event> bookingsFromMap = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "inside onclick " + dateFormatForDisplaying.format(dateClicked));
                if (bookingsFromMap != null) {
                    Log.d(TAG, bookingsFromMap.toString());
                    informacoes.clear();
                    for (Event booking : bookingsFromMap) {
                        informacoes.add((String) booking.getData());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                toolbar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));


            }
        });
        final View.OnClickListener showCalendarOnClickLis = getCalendarShowLis();
        slideCalendarBut.setOnClickListener(showCalendarOnClickLis);

        compactCalendarView.setAnimationListener(new CompactCalendarView.CompactCalendarAnimationListener() {
            @Override
            public void onOpened() {
                slideCalendarBut.setText("Minimizar");

            }

            @Override
            public void onClosed() {
                slideCalendarBut.setText("Maximizar");
            }

        });

    }


    private View.OnClickListener getCalendarShowLis() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!compactCalendarView.isAnimating()) {
                    if (shouldShow) {
                        compactCalendarView.showCalendar();
                    } else {
                        compactCalendarView.hideCalendar();
                    }
                    shouldShow = !shouldShow;
                }
            }
        };
    }

    private void fetchPosts() {
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostLoaded, onPostsError);
        queue.add(request);
    }

    private final Response.Listener<String> onPostLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Type listType = new TypeToken<ArrayList<CalendarioDAO>>() {
            }.getType();
            listCalendario = new Gson().fromJson(response, listType);

            adicionarDatas();
        }

    };

    private final Response.ErrorListener onPostsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("CalendarioGRADActivity", error.toString());
        }
    };

    public void adicionarDatas() {

        List<Event> eventos = new ArrayList<>();


        if (listCalendario == null) {
            System.out.println("Lista Nulla!!");
        } else {
            for (CalendarioDAO calen : listCalendario) {
                for (CalendarioDAO.EventsBean event : calen.getEvents()) {

                    if (event.getEndTime() != 0){

                        for (long i = event.getStartTime() + 86400000; i <= event.getEndTime(); i = i + 86400000){
                          
                          eventos.add(new Event(Color.RED, i, event.getTitle()));
                          
                        }

                    }
                    eventos.add(new Event(Color.BLUE, event.getStartTime(), event.getTitle()));
                    
                }
            }

            compactCalendarView.addEvents(eventos);

        }


    }
}
