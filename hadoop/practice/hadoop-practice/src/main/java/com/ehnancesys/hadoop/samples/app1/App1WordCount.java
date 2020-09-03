package com.ehnancesys.hadoop.samples.app1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class App1WordCount {
	public static void main(String[] args) throws Exception {
			Configuration configuration = new Configuration();
			Job job = Job.getInstance();
			job.setJobName("WordCount");
			job.setJarByClass(App1WordCount.class);
			job.setMapperClass(App1Mapper.class);
			job.setReducerClass(App1Reducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);

			System.out.println("Job Name   : " + job.getJobName());
			System.out.println("Input  dir : " + args[0]);
			System.out.println("Output dir : " + args[1]);

			Path outputPath = new Path(args[1]);
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			outputPath.getFileSystem(configuration).delete(outputPath, false);
			System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
