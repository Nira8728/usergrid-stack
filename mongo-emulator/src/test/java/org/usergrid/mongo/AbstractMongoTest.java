/*******************************************************************************
 * Copyright 2012 Apigee Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.usergrid.mongo;

import java.net.UnknownHostException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usergrid.persistence.cassandra.EntityManagerFactoryImpl;
import org.usergrid.services.ServiceManagerFactory;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

public abstract class AbstractMongoTest
{
	private static Logger LOG = LoggerFactory.getLogger(AbstractMongoTest.class);

	static MongoServer server = null;
	static boolean usersSetup = false;
	protected static Properties properties;

	protected static String access_token;

	EntityManagerFactoryImpl emf;
	ServiceManagerFactory smf;


    public AbstractMongoTest()
    {
		super();
		smf = new ServiceManagerFactory(emf, properties, null, null, null);
	}


	/**
	 * Get a db instance for testing
	 * @return
	 * @throws UnknownHostException
	 * @throws MongoException
	 */
	public static DB getDb() throws UnknownHostException, MongoException
    {
	    Mongo m = new Mongo("localhost", 27017);
        m.setWriteConcern(WriteConcern.SAFE);

        DB db = m.getDB("test-organization/test-app");
        db.authenticate("test", "test".toCharArray());
        return db;
	}
}
