/*
 * Copyright 2018 Otavio R. Piske <angusyoung@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.maestro.common.test;

import org.maestro.common.test.properties.annotations.PropertyConsumer;
import org.maestro.common.test.properties.annotations.PropertyName;
import org.maestro.common.test.properties.annotations.PropertyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test properties used/saved by inspectors
 */
@SuppressWarnings("ALL")
@PropertyName(name = "")
public class InspectorProperties {
    public static String FILENAME = "inspector.properties";
    public static int UNSET_INT = 0;

    private static final Logger logger = LoggerFactory.getLogger(InspectorProperties.class);

    private String jvmName;
    private String jvmVersion;
    private String jvmPackageVersion;
    private String operatingSystemName;
    private String operatingSystemArch;
    private String operatingSystemVersion;
    private long systemCpuCount;
    private long systemMemory;
    private long systemSwap;
    private String productName;
    private String productVersion;

    @PropertyProvider(name="jvmName", join = false)
    public String getJvmName() {
        return jvmName;
    }

    @PropertyConsumer(name="jvmName", join = false)
    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }


    @PropertyProvider(name="jvmVersion", join = false)
    public String getJvmVersion() {
        return jvmVersion;
    }

    @PropertyConsumer(name="jvmVersion", join = false)
    public void setJvmVersion(String jvmVersion) {
        this.jvmVersion = jvmVersion;
    }

    @PropertyProvider(name="jvmPackageVersion", join = false)
    public String getJvmPackageVersion() {
        return jvmPackageVersion;
    }

    @PropertyConsumer(name="jvmPackageVersion", join = false)
    public void setJvmPackageVersion(String jvmPackageVersion) {
        this.jvmPackageVersion = jvmPackageVersion;
    }

    @PropertyProvider(name="operatingSystemName", join = false)
    public String getOperatingSystemName() {
        return operatingSystemName;
    }

    @PropertyConsumer(name="operatingSystemName", join = false)
    public void setOperatingSystemName(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }

    @PropertyProvider(name="operatingSystemArch", join = false)
    public String getOperatingSystemArch() {
        return operatingSystemArch;
    }

    @PropertyConsumer(name="operatingSystemArch", join = false)
    public void setOperatingSystemArch(String operatingSystemArch) {
        this.operatingSystemArch = operatingSystemArch;
    }

    @PropertyProvider(name="operatingSystemVersion", join = false)
    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    @PropertyConsumer(name="operatingSystemVersion", join = false)
    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }

    @PropertyProvider(name="systemCpuCount", join = false)
    public long getSystemCpuCount() {
        return systemCpuCount;
    }

    public void setSystemCpuCount(long systemCpuCount) {
        this.systemCpuCount = systemCpuCount;
    }

    @PropertyConsumer(name="systemCpuCount", join = false)
    public void setSystemCpuCount(String systemCpuCount) {
        setSystemCpuCount(Long.parseLong(systemCpuCount));
    }

    @PropertyProvider(name="systemMemory", join = false)
    public long getSystemMemory() {
        return systemMemory;
    }

    public void setSystemMemory(long systemMemory) {
        this.systemMemory = systemMemory;
    }

    @PropertyConsumer(name="systemMemory", join = false)
    public void setSystemMemory(final String systemMemory) {
        setSystemMemory(Long.parseLong(systemMemory));
    }

    @PropertyProvider(name="systemSwap", join = false)
    public long getSystemSwap() {
        return systemSwap;
    }

    public void setSystemSwap(long systemSwap) {
        this.systemSwap = systemSwap;
    }

    @PropertyConsumer(name="systemSwap", join = false)
    public void setSystemSwap(String systemSwap) {
        setSystemSwap(Long.parseLong(systemSwap));
    }

    @PropertyProvider(name="productName", join = false)
    public String getProductName() {
        return productName;
    }

    @PropertyConsumer(name="productName", join = false)
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @PropertyProvider(name="productVersion", join = false)
    public String getProductVersion() {
        return productVersion;
    }

    @PropertyConsumer(name="productVersion", join = false)
    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }


}
