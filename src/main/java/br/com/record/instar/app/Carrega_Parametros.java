package br.com.record.instar.app;

import br.com.record.instar.conf.Carrega_Conf;

public class Carrega_Parametros {

    public static Carrega_Conf loadParametersSIS(String[] args) throws Exception {
        Carrega_Conf loadTags = new Carrega_Conf();
        loadSIS(args, loadTags);
        return loadTags;
    }

    public static Carrega_Conf loadParametersKantar(String[] args) throws Exception {
        Carrega_Conf loadTags = new Carrega_Conf();
        loadKantar(args, loadTags);
        return loadTags;
    }

    public static Carrega_Conf loadParametersJson(String[] args) throws Exception {
        Carrega_Conf loadTags = new Carrega_Conf();
        loadJson(args, loadTags);
        return loadTags;
    }

    private static void loadSIS(String[] args, Carrega_Conf loadTags) {
        loadTags.carregaSIS.directory_home = args[5];
        loadTags.carregaSIS.directory_bruto = args[7];
        loadTags.carregaSIS.directory_tratado = args[9];
        loadTags.carregaSIS.directory_jobs = args[11];
    }

    private static void loadKantar(String[] args, Carrega_Conf loadTags) {
        loadTags.carregaKantar.type = args[27];
        loadTags.carregaKantar.origin = args[29];
        loadTags.carregaKantar.url = args[31];
        loadTags.carregaKantar.user_api = args[33];
        loadTags.carregaKantar.pass_api = args[35];
        loadTags.carregaKantar.idLang = args[37];
        loadTags.carregaKantar.idApp = args[39];
        loadTags.carregaKantar.out_format = args[41];
        loadTags.carregaKantar.temp_path = args[43];
    }

    private static void loadJson(String[] args, Carrega_Conf loadTags) {
        loadTags.carregaJson.val_data = args[3];
        loadTags.carregaJson.description = args[17];
        loadTags.carregaJson.timestamp = args[19];
        loadTags.carregaJson.frequency = args[21];
        loadTags.carregaJson.source_type = args[23];
        loadTags.carregaJson.source_origin = args[25];
        loadTags.carregaJson.source_select = args[45];
        loadTags.carregaJson.source_from = args[47];
        loadTags.carregaJson.keyDate = args[49];
        loadTags.carregaJson.valueDate = args[51];
        loadTags.carregaJson.keySector = args[53];
        loadTags.carregaJson.valueSector = args[55];
        loadTags.carregaJson.keyTarget = args[57];
        loadTags.carregaJson.valueTarget = args[59];
        loadTags.carregaJson.keyChannel = args[61];
        loadTags.carregaJson.valueChannel = args[63];
        loadTags.carregaJson.keyLevel = args[65];
        loadTags.carregaJson.valeuLevel = args[67];
        loadTags.carregaJson.keyTimebands = args[69];
        loadTags.carregaJson.valueTimebands = args[71];
        loadTags.carregaJson.source_groupby = args[73];
        loadTags.carregaJson.source_is_asynchronous = args[75];
        loadTags.carregaJson.target_type = args[77];
        loadTags.carregaJson.target_table = args[79];
        loadTags.carregaJson.target_path = args[81];
        loadTags.carregaJson.target_delimited_sep = args[83];
        loadTags.carregaJson.target_partition_field = args[85];
        loadTags.carregaJson.mask = args[87];
    }



}
