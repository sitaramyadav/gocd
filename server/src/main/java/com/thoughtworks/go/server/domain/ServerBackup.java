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

package com.thoughtworks.go.server.domain;

import com.thoughtworks.go.domain.PersistentObject;

import java.util.Date;

/**
 * @understands A single backup of the server
 */
public class ServerBackup extends PersistentObject{
    private Date time;
    private String path;
    private String username;
    private BackupStatus status;
    private String message;

    private ServerBackup() {
    }

    public ServerBackup(String path, Date time, String username, String message) {
        this(path, time, username, message, BackupStatus.IN_PROGRESS);
    }

    public ServerBackup(String path, Date time, String username, String message, BackupStatus status) {
        this.path = path;
        this.time = time;
        this.username = username;
        this.message = message;
        this.status = status;
    }

    public ServerBackup(String path, Date time, String username, BackupStatus status, String message, long id) {
        this.path = path;
        this.time = time;
        this.username = username;
        this.message = message;
        this.status = status;
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public Date getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServerBackup that = (ServerBackup) o;

        if (path != null ? !path.equals(that.path) : that.path != null) {
            return false;
        }
        if (time != null ? !time.equals(that.time) : that.time != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) {
            return false;
        }
        if (message != null ? !message.equals(that.message) : that.message != null) {
            return false;
        }
        if (username != null ? !username.equals(that.username) : that.username != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    public BackupStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessful() {
        return BackupStatus.COMPLETED.equals(status);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void markCompleted() {
        this.status = BackupStatus.COMPLETED;
    }

    public void markError(String message) {
        this.status = BackupStatus.ERROR;
        this.message = message;
    }

    public Boolean hasFailed() {
        return BackupStatus.ERROR.equals(status);
    }
}
