/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.mapred.lib;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;
import org.apache.hadoop.util.Progressable;

/**
 * This class extends the MultipleOutputFormat, allowing to write the output data 
 * to different output files in sequence file output format. 
 */
public class MultipleSequenceFileOutputFormat 
extends MultipleOutputFormat<WritableComparable, Writable> {

  private SequenceFileOutputFormat theSequenceFileOutputFormat = null;
  
  @Override
  @SuppressWarnings("unchecked") 
  protected RecordWriter<WritableComparable, Writable> getBaseRecordWriter(
                                                         FileSystem fs,
                                                         JobConf job,
                                                         String name,
                                                         Progressable arg3) 
  throws IOException {
    if (theSequenceFileOutputFormat == null) {
      theSequenceFileOutputFormat = new SequenceFileOutputFormat();
    }
    return theSequenceFileOutputFormat.getRecordWriter(fs, job, name, arg3);
  }
}
