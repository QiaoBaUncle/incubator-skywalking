/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.collector.client.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.skywalking.apm.collector.client.Client;

/**
 * @author peng-yongsheng
 */
public class GRPCClient implements Client {

    private final String host;

    private final int port;

    private ManagedChannel channel;

    public GRPCClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override public void initialize() {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
    }

    @Override public void shutdown() {
        channel.shutdownNow();
    }

    public ManagedChannel getChannel() {
        return channel;
    }

    @Override public String toString() {
        return host + ":" + port;
    }
}
