package br.com.record.instar.app;

import br.com.record.instar.conf.Carrega_Conf;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import br.com.record.instar.hive.DadosBrutos;


public class Instar_Ingestion {

    public static void main(String[] args) throws Exception {

        Carrega_Conf SIS = Carrega_Parametros.loadParametersSIS(args);
        Carrega_Conf Kantar = Carrega_Parametros.loadParametersKantar(args);
        Carrega_Conf Json = Carrega_Parametros.loadParametersJson(args);

        try {
            String queryDate = Json.carregaJson.val_data;


            String qryRequest = Json.carregaJson.source_select+" FROM "+
                    "SELECT INGR_CHANNELS.ATTR_ID, INGR_CHANNELS.ATTR_NAME, INGR_CHANNELS.ATTR_ID49703 FROM INGR_CHANNELS";
            URL url = new URL("https://api2.instarsuite.com/ibope_brasil/IADS.asmx/GetData?iads_params=name:RECORD_TV_API_1;password:2Hqrv2Ak;idLang:EN;idApp:3000;outformat:CSV;skipmetadata:1&tq=SELECT%20INGR_CHANNELS.ATTR_ID,%20INGR_CHANNELS.ATTR_NAME,%20INGR_CHANNELS.ATTR_ID49703%20FROM%20INGR_CHANNELS");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/instar");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    }
