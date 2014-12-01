/**
 * Copyright (C) 2014 meltmedia (christian.trimble@meltmedia.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.meltmedia.dropwizard.mongo;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class MongoHealthCheck extends HealthCheck {

  MongoClient mongoClient;
  MongoConfiguration configuration;

  public MongoHealthCheck(MongoClient mongoClient, MongoConfiguration configuration) {
    this.mongoClient = mongoClient;
    this.configuration = configuration;
  }

  @Override
  protected Result check() throws Exception {
    try {
      mongoClient.getDB(configuration.getDatabase()).command("isMaster");
      return Result.healthy();
    }
    catch( MongoException me ) {
      return Result.unhealthy("cannot access database");
    }
  }
}
