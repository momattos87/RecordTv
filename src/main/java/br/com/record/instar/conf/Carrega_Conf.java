package br.com.record.instar.conf;

public class Carrega_Conf {

    public Carrega_SIS carregaSIS = null;
    public Carrega_Kantar carregaKantar = null;
    public Carrega_Json carregaJson = null;

    public Carrega_SIS getCarregaSIS() {
        return carregaSIS;
    }
    public void setCarregaSIS(Carrega_SIS carregaSIS) {
        this.carregaSIS = carregaSIS;
    }

    public Carrega_Kantar getCarregaKantar() {
        return carregaKantar;
    }
    public void setCarregaKantar(Carrega_Kantar carregaKantar) {
        this.carregaKantar = carregaKantar;
    }

    public Carrega_Json getCarregaJson() {
        return carregaJson;
    }
    public void setCarregaJson(Carrega_Json carregaJson) {
        this.carregaJson = carregaJson;
    }

    /**
     * Instanciando Carrega_Conf.
     */
    public Carrega_Conf() {
        this.carregaSIS = new Carrega_SIS();
        this.carregaKantar = new Carrega_Kantar();
        this.carregaJson = new Carrega_Json();

    }

    @Override
    public String toString() {
        return "Carrega_Conf [carregaSIS=" + carregaSIS + ", carregaKantar=" + carregaKantar
                + ", carregaJson=" + carregaJson  +"]";
    }

}
