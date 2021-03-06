/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.util;

import com.thoughtworks.go.utils.Timeout;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SystemTimeClock implements Clock, Serializable {
    public Date currentTime() {
        return new Date();
    }

    public DateTime currentDateTime() {
        return new DateTime(currentTime());
    }

    @Override
    public Timestamp currentTimestamp() {
        return new Timestamp(currentTimeMillis());
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public void sleepForSeconds(long seconds) throws InterruptedException {
        sleepForMillis(seconds * 1000);
    }

    public void sleepForMillis(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public DateTime timeoutTime(Timeout timeout) {
        return timeoutTime(timeout.inMillis());
    }

    public DateTime timeoutTime(long timeoutInMillis) {
        return new DateTime().plusMillis((int) timeoutInMillis);
    }
}
