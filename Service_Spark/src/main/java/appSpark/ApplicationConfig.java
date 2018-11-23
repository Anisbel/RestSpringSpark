package appSpark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;



public class ApplicationConfig {

    public  SparkConf sparkConf;

    public ApplicationConfig() {

        if(this.sparkConf==null) {
            sparkConf();
        }
    }

    public void sparkConf() {

        SparkConf sparkConf = new SparkConf()
                .setAppName("alo")
                //Run Spark locally with 2 worker threads
                .setMaster("local[2]")
                .set("spark.cassandra.connection.host", "127.0.0.1");

         this.sparkConf=sparkConf;
    }


    @Bean
    public SparkContext javaSparkContext() {
        return new SparkContext(this.sparkConf);
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
