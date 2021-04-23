package br.com.record.instar.hive

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SaveMode, SparkSession}

import java.io.File

object DadosBrutos {

  //Criando o Logger4J
  @transient lazy val logger: Logger = Logger.getLogger( getClass.getName )
  logger.setLevel( Level.DEBUG )


  def main(args: Array[String]): Unit = {

    val warehouseLocation = new File( "spark-warehouse" ).getAbsolutePath

    //Abrindo sessao do spark
    val spark = SparkSession
      .builder()
      .config( "spark.sql.warehouse.dir", warehouseLocation )
      .enableHiveSupport()
      .getOrCreate()

    import spark.sql


    //Recuperando dados do csv no dataframe
    val df = spark.read
      .option( "header", "true" )
      .option( "inferSchema", "true" )
      .option( "delimiter", ";" )
      .csv( s"${parameter.inputvaltemppath( args )}" + s"${parameter.inputtargettable( args )}" + s"${parameter.inputvalueDate( args )}" + ".csv" )

    //Tratando itens do parametro de select
    val column: String = s"${parameter.inputsourceselect( args )}"
    val z = column.split( "," )
    var col: Array[String] = null
    if (z.indices != z.length) {
      col = z.map( _ + " STRING, " )
    }
    if (z.indices == z.length) {
      col = z.map( _ + " STRING " )
    }

    //Criando estrutura
    try {
      sql( s"CREATE DATABASE IF NOT EXISTS DB_STAGE LOCATION '/.../stage.db'" )
      sql( s"CREATE EXTERNAL TABLE IF NOT EXISTS ${parameter.inputtargettable( args )}($col) PARTITIONED BY (${parameter.inputpartitionfield( args )} STRING COMMENT 'Data de extracao dos dados na origem no formato YYYY-MM-DD') ROW FORMAT DELIMITED FIELDS TERMINATED BY ';' STORED AS PARQUET LOCATION '${parameter.inputtargetpath( args )}'" )
      logger.info( "Criando external table se necessario..." )
    } catch {
      case e: Throwable =>
        logger.error( "Erro na criação do database e/ou external table hive." )
        throw e
    }

    logger.info( "Gravando dados no Hive..." )

    //Gravando os dados
    try {
      df.write.mode( SaveMode.Append ).partitionBy( s"${parameter.inputpartitionfield( args )}" ).format( "br/com/record/instar/hive" ).saveAsTable( s"${parameter.inputtargettable( args )}" )
      logger.info( "Gravação dos dados no Hive efetuada com sucesso!!" )

    } catch {
      case e: Throwable =>
        logger.error( "Erro de gravação na tabela: " + s"${parameter.inputtargettable( args )}" )
        throw e
    }
    spark.stop()

  }


}