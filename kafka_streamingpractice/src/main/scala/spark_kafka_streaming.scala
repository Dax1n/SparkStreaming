/**
  * Created by subhra on 12/10/16.
  */
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils

object sparkkafkastreaming {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("KafkaReceiver")
    val ssc = new StreamingContext(conf, Seconds(10))

//    // Set the existing SparkContext's Master, AppName and other params
//    sc.getConf.setMaster("local[2]").setAppName("NetworkWordCount").set("spark.ui.port", "44040" )
//    // Use 'sc' to create a Streaming context with 2 second batch interval
//    val ssc = new StreamingContext(sc, Seconds(2) )

    val kafkaStream = KafkaUtils.createStream(ssc, "localhost:2181","spark-streaming-consumer-group", Map("spark-topic" -> 5))
    kafkaStream.print()
    ssc.start

    ssc.awaitTermination()
  }

}
