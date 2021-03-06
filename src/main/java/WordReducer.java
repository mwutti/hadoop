import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by michael on 27.05.15.
 */
public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    protected static final String TARGET_WORD = "Name";

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        if (containsTargetWord(key)) {
            int wordCount = 0;
            for (IntWritable value: values) {
                wordCount += value.get();
            }
            context.write(key, new IntWritable(wordCount));
        }
    }

    private boolean containsTargetWord(Text key) {
        return key.toString().equals(TARGET_WORD);
    }
}
