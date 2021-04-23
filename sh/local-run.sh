#!/bin/bash

ABN=$1
JOBNAME=$2

function jsonValue() {
KEY=$1
num=$2
awk -F"[,:}]" '{for(i=1;i<=NF;i++){if($i~/'$KEY'\042/){print $(i+1)}}}' | tr -d '"' | sed -n ${num}p
}

BASEDIR="$( cd "$( dirname "${0}" )" && pwd )"
val_data=$(date-1 +%Y%m%d)
val_directory_home="$(cat ${BASEDIR}/config/parameters/sis/parametersSIS_${ABN}.json | jsonValue directory_home)"
val_directory_bruto="$(cat ${BASEDIR}/config/parameters/sis/parametersSIS_${ABN}.json | jsonValue directory_bruto)"
val_directory_tratado="$(cat ${BASEDIR}/config/parameters/sis/parametersSIS_${ABN}.json | jsonValue directory_tratado)"
val_directory_jobs="$(cat ${BASEDIR}/config/parameters/sis/parametersSIS_${ABN}.json | jsonValue directory_jobs)"

BASEDIR_JOB=${val_directory_jobs}
val_version="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue version)"
val_project="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue project)"
val_description="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue description)"
val_timestamp="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue timestamp)"
val_frequency="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue frequency)"
val_source_type="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue source_type)"
val_source_origin="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue source_origin)"

val_type="$(cat ${BASEDIR}/config/parameters/sis/${val_source_origin}.json | jsonValue type)"
val_origin="$(cat ${BASEDIR}/config/parameters/sis/${val_source_origin}.json | jsonValue origin)"
val_url="$(cat ${BASEDIR}/config/parameters/sis/${val_source_origin}.json | jsonValue url)"
val_user_api="$(cat ${BASEDIR}/config/parameters/sis/${val_source_origin}.json | jsonValue user_api)"
val_pass_api="$(cat ${BASEDIR}/config/parameters/sis/${val_source_origin}.json | jsonValue pass_api)"
val_idLang="$(cat ${BASEDIR}/config/parameters/sis/${val_source_origin}.json | jsonValue idLang)"
val_idApp="$(cat ${BASEDIR}/config/parameters/sis/${val_source_origin}.json | jsonValue idApp)"
val_out_format="$(cat ${BASEDIR}/config/parameters/sis/${val_source_origin}.json | jsonValue out_format)"
val_temp_path="$(cat ${BASEDIR}/config/parameters/sis/${val_source_origin}.json | jsonValue temp_path)"

val_source_select="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue source_select)"
val_source_from="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue source_from)"
val_keyDate="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue keyDate)"
val_valueDate="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue valueDate)"
val_keySector="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue keySector)"
val_valueSector="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue valueSector)"
val_keyTarget="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue keyTarget)"
val_valueTarget="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue valueTarget)"
val_keyChannel="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue keyChannel)"
val_valueChannel="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue valueChannel)"
val_keyLevel="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue keyLevel)"
val_valueLevel="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue valeuLevel)"
val_keyTimebands="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue keyTimebands)"
val_valueTimebands="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue valueTimebands)"
val_source_groupby="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue source_groupby)"
val_source_is_asynchronous="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue source_is_asynchronous)"
val_target_type="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue target_type)"
val_target_table="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue target_table)"
val_target_path="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue target_path)"
val_target_delimited_sep="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue target_delimited_sep)"
val_target_partition_field="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue target_partition_field)"
val_mask="$(cat ${BASEDIR}/config${BASEDIR_JOB}/${JOBNAME}.json | jsonValue mask)"

echo "--------------------------------"

SPARK_PROPERTIES="${BASEDIR}/config/sparkDevLocal.conf"
#SPARK_PROPERTIES="${BASEDIR}/config/sparkDevYarn.conf"

time spark-submit \
    --class=br.com.record.instar.app.Instar_Ingestion \
    --properties-file ${SPARK_PROPERTIES} \
    ${BASEDIR}/../target/scala-2.11/recordtv_2.11-0.1.jar \
    --input_Ambiente_Runing ${ABN} \
    --input_val_data ${val_data} \
    --input_Directory_home ${val_directory_home} \
    --input_Directory_bruto ${val_directory_bruto} \
    --input_Directory_tratado ${val_directory_tratado} \
    --input_Directory_jobs ${val_directory_jobs} \
    --input_val_version ${val_version} \
    --input_val_project ${val_project} \
    --input_val_description ${val_description} \
    --input_val_timestamp ${val_timestamp} \
    --input_val_frequency ${val_frequency} \
    --input_val_source_type ${val_source_type} \
    --input_val_source_origin ${val_source_origin} \
    --input_val_type ${val_type} \
    --input_val_origin ${val_origin} \
    --input_val_url ${val_url} \
    --input_val_user_api ${val_user_api} \
    --input_val_pass_api ${val_pass_api} \
    --input_val_idLang ${val_idLang} \
    --input_val_idApp ${val_idApp} \
    --input_val_out_format ${val_out_format} \
    --input_val_temp_path ${val_temp_path} \
    --input_val_source_select ${val_source_select} \
    --input_val_source_from ${val_source_from} \
    --input_val_keyDate ${val_keyDate} \
    --input_val_valueDate ${val_valueDate} \
    --input_val_keySector ${val_keySector} \
    --input_val_valueSector ${val_valueSector} \
    --input_val_keyTarget ${val_keyTarget} \
    --input_val_valueTarget ${val_valueTarget} \
    --input_val_keyChannel ${val_keyChannel} \
    --input_val_valueChannel ${val_valueChannel} \
    --input_val_keyLevel ${val_keyLevel} \
    --input_val_valueLevel ${val_valueLevel} \
    --input_val_keyTimebands ${val_keyTimebands} \
    --input_val_valueTimebands ${val_valueTimebands} \
    --input_val_source_groupby ${val_source_groupby} \
    --input_val_source_is_asynchronous ${val_source_is_asynchronous} \
    --input_val_target_type ${val_target_type} \
    --input_val_target_table ${val_target_table} \
    --input_val_target_path ${val_target_path} \
    --input_val_target_delimited_sep ${val_target_delimited_sep} \
    --input_val_target_partition_field ${val_target_partition_field} \
    --input_val_mask ${val_mask}

