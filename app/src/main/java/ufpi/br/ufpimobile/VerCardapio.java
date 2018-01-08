package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class VerCardapio extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cardapio);

        Intent in = getIntent();
        int i = in.getIntExtra("dia",-1);

        //Alterar o texto da toolbar para a data selecionada
        toolbar = (Toolbar) findViewById(R.id.toolbar_VerCardapio);

        switch (i) {
            case 1: toolbar.setTitle("Cardápio de Segunda-Feira");
                break;
            case 2: toolbar.setTitle("Cardápio de Terça-Feira");
                break;
            case 3: toolbar.setTitle("Cardápio de Quarta-Feira");
                break;
            case 4: toolbar.setTitle("Cardápio de Quinta-Feira");
                break;
            case 5: toolbar.setTitle("Cardápio de Sexta-Feira");
                break;
            case 6: toolbar.setTitle("Cardápio de Sábado");
                break;
                default: toolbar.setTitle("Cardápio da Semana");
        }
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), CardapioSemana.class);
                finish();
                startActivity(home);
            }
        });


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class CardapioFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        public CardapioFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static CardapioFragment newInstance(String pratoPrincipal) {
            CardapioFragment fragment = new CardapioFragment();
            Bundle args = new Bundle();
            args.putString("prato", pratoPrincipal);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ver_cardapio, container, false);
            TextView pratoP = (TextView) rootView.findViewById(R.id.pratoPrinc);
            pratoP.setText(getArguments().getString("prato"));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return CardapioFragment.newInstance("Fritada Mista");
                case 1: return CardapioFragment.newInstance("Bife Pomodoro");
                case 2: return CardapioFragment.newInstance("Frango Assado");
            }
            return CardapioFragment.newInstance("FECHADO");
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 3;
        }
    }
}
