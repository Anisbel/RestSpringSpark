package appSpark;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapColumnTo;
import org.apache.spark.SparkConf;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import org.apache.spark.mllib.fpm.AssociationRules;
import org.apache.spark.mllib.fpm.FPGrowth;
import org.apache.spark.mllib.fpm.FPGrowthModel;


@Component
public class Calculator {


    //UTILISATION DE L'ALGORITHME FPGROWTH SELON LA SOURCE :
    //https://spark.apache.org/docs/latest/mllib-frequent-pattern-mining.html
    public String count() {

        //les éléments les plus fréquents seront ajouter a cette String
        String frequentElement="";

        ApplicationConfig appConfig= new ApplicationConfig();

        SparkContext sc = appConfig.javaSparkContext();

        JavaRDD<String> data = CassandraJavaUtil.javaFunctions(sc)
                .cassandraTable("javasampleapproach", "produit", mapColumnTo(String.class))
        .select("nameproduit");

        JavaRDD<List<String>> transactions = data.map(line -> Arrays.asList(line.split(" ")));

        FPGrowth fpg = new FPGrowth()
                .setMinSupport(0.2)
                .setNumPartitions(10);
        FPGrowthModel<String> model = fpg.run(transactions);

        for (FPGrowth.FreqItemset<String> itemset: model.freqItemsets().toJavaRDD().collect()) {
            System.out.println("[" + itemset.javaItems() + "], " + itemset.freq());
            frequentElement+= itemset.javaItems()+": "+itemset.freq()+ System.getProperty("line.separator");
        }
        System.out.println(frequentElement);
        double minConfidence = 0.8;
        for (AssociationRules.Rule<String> rule
                : model.generateAssociationRules(minConfidence).toJavaRDD().collect()) {
            System.out.println(
                    rule.javaAntecedent() + " => " + rule.javaConsequent() + ", " + rule.confidence());
        }


         sc.stop();

        return frequentElement ;
    }
}