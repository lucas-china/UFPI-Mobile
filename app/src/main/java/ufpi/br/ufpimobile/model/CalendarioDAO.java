package ufpi.br.ufpimobile.model;


import java.util.List;

/**
 * Created by lucas_brito on 30/12/17.
 */

public class CalendarioDAO {


    /**
     * year : 2017
     * events : [{"title":"Solicitação online da Oferta de Disciplinas para o Período Letivo 2017.4 pelas Coordenações de Cursos","startDate":"2017-12-18T03:00:00.000Z","startTime":1513566000000},{"title":"Confirmação da Oferta de Disciplinas para o Período 2017.4 pelos Departamentos de Ensino e Coordenações de Curso","startDate":"2017-12-19T03:00:00.000Z","startTime":1513652400000},{"title":"Matrícula Compulsória pelas Coordenações de Curso para o Período Letivo Especial 2017.4","startDate":"2017-12-20T03:00:00.000Z","endDate":"2017-12-21T03:00:00.000Z","startTime":1513738800000,"endTime":1513825200000},{"title":"Início das aulas do Período Letivo 2017.4","startDate":"2017-12-20T03:00:00.000Z","startTime":1513738800000},{"title":"Conclusão das aulas do Período Letivo 2017.4","startDate":"2018-01-26T03:00:00.000Z","startTime":1516935600000},{"title":"Digitação do Resultado Final do Rendimento Acadêmico dos alunos referente ao Período Letivo 2017.4 pelos Docentes","startDate":"2018-01-26T03:00:00.000Z","endDate":"2018-01-27T03:00:00.000Z","startTime":1516935600000,"endTime":1517022000000},{"title":"Último dia para Realização do Exame Final do Período Letivo 2017.4","startDate":"2018-01-27T03:00:00.000Z","startTime":1517022000000}]
     * title : Calendário Acadêmico dos Cursos de Graduação 2017.4
     * createdAt : 2018-01-02T15:08:24.132Z
     */

    private int year;
    private String title;
    private String createdAt;
    private List<EventsBean> events;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<EventsBean> getEvents() {
        return events;
    }

    public void setEvents(List<EventsBean> events) {
        this.events = events;
    }

    public static class EventsBean {
        /**
         * title : Solicitação online da Oferta de Disciplinas para o Período Letivo 2017.4 pelas Coordenações de Cursos
         * startDate : 2017-12-18T03:00:00.000Z
         * startTime : 1513566000000
         * endDate : 2017-12-21T03:00:00.000Z
         * endTime : 1513825200000
         */

        private String title;
        private String startDate;
        private long startTime;
        private String endDate;
        private long endTime;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }
    }
}
