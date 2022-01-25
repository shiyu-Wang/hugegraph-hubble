/*
 *
 *  * Copyright 2017 HugeGraph Authors
 *  *
 *  * Licensed to the Apache Software Foundation (ASF) under one or more
 *  * contributor license agreements. See the NOTICE file distributed with this
 *  * work for additional information regarding copyright ownership. The ASF
 *  * licenses this file to You under the Apache License, Version 2.0 (the
 *  * "License"); you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  * License for the specific language governing permissions and limitations
 *  * under the License.
 *
 */

package com.baidu.hugegraph.controller.auth;

import com.baidu.hugegraph.common.Constant;
import com.baidu.hugegraph.config.HugeConfig;
import com.baidu.hugegraph.controller.BaseController;
import com.baidu.hugegraph.options.HubbleOptions;
import com.baidu.hugegraph.util.E;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(Constant.API_VERSION + "monitor")
public class MonitorController extends BaseController {

    @Autowired
    private HugeConfig config;

    @GetMapping
    public void monitor(HttpServletResponse httpServletResponse) {
        String monitorURL = config.get(HubbleOptions.MONITOR_URL);

        E.checkArgument(!Strings.isEmpty(monitorURL), "No config " +
                "\"monitor.url\" in config file: hugegraph-hubble.properties");

        httpServletResponse.setStatus(302);
        httpServletResponse.setHeader("Location", monitorURL);
    }
}