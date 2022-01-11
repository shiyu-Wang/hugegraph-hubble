/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.controller.space;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.hugegraph.common.Constant;
import com.baidu.hugegraph.controller.BaseController;
import com.baidu.hugegraph.service.space.OLTPServerService;
import com.baidu.hugegraph.driver.HugeClient;
import com.baidu.hugegraph.structure.space.OLTPService;

@RestController
@RequestMapping(Constant.API_VERSION + "graphspaces/{graphspace}/services/oltp")
public class ServiceController extends BaseController {

    @Autowired
    OLTPServerService oltpService;

    @GetMapping
    public Object queryPage(@PathVariable("graphspace") String graphspace,
                            @RequestParam(name = "query", required = false,
                                    defaultValue = "") String query,
                            @RequestParam(name = "page_no", required = false,
                                    defaultValue = "1") int pageNo,
                            @RequestParam(name = "page_size", required = false,
                                    defaultValue = "10") int pageSize) {
        return oltpService.queryPage(this.authClient(graphspace, null),
                                           query, pageNo, pageSize);
    }

    @GetMapping("{service}")
    public Object get(@PathVariable("graphspace") String graphspace,
                      @PathVariable("service") String service) {
        return oltpService.get(this.authClient(graphspace, null),
                                   service);
    }

    @PostMapping("{service}")
    public Object update(@PathVariable("graphspace") String graphspace,
                         @PathVariable("service") String service,
                         @RequestBody OLTPService serviceEntity) {

        serviceEntity.setName(graphspace);

        return null;
    }

    @DeleteMapping("{service}")
    public void delete(@PathVariable("graphspace") String graphspace,
                       @PathVariable("service") String service) {
        HugeClient client = this.authClient(graphspace, null);
        oltpService.delete(client, service);
    }

}