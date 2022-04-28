// Copyright 2013 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.net;

import static org.chromium.net.test.CertTestUtil.CERTS_DIRECTORY;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

import android.util.Log;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.core.app.ApplicationProvider;
import org.chromium.net.test.CertTestUtil;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 * Tests for org.chromium.net.X509Util.
 */
@RunWith(AndroidJUnit4.class)
public class X509UtilTest {
  private static final String BAD_EKU_TEST_ROOT = "eku-test-root.pem";
  private static final String CRITICAL_CODE_SIGNING_EE = "crit-codeSigning-chain.pem";
  private static final String NON_CRITICAL_CODE_SIGNING_EE = "non-crit-codeSigning-chain.pem";
  private static final String WEB_CLIENT_AUTH_EE = "invalid_key_usage_cert.der";
  private static final String OK_CERT = "ok_cert.pem";
  private static final String GOOD_ROOT_CA = "root_ca_cert.pem";

  private static byte[] readFileBytes(String pathname) throws IOException {
    RandomAccessFile file = new RandomAccessFile(pathname, "r");
    byte[] bytes = new byte[(int)file.length()];
    int bytesRead = file.read(bytes);
    if (bytesRead != bytes.length) {
      return Arrays.copyOfRange(bytes, 0, bytesRead);
    }
    return bytes;
  }

  @Before
  public void setUp() throws Exception {
    System.setProperty("robolectric.logging", "stdout");
    ContextUtils.initApplicationContextForTests(ApplicationProvider.getApplicationContext());
  }

  @After
  public void tearDown() {
    try {
      X509Util.clearTestRootCertificates();
    } catch (Exception e) {
      Assert.fail("Could not clear test root certificates: " + e.toString());
    }
  }

  @Test
  public void testEkusVerified() throws GeneralSecurityException, IOException {
    byte[] rootCert =
        CertTestUtil.pemToDer("test/java/org/chromium/net/testing/data/root_ca_cert.pem");
    X509Util.addTestRootCertificate(rootCert);
    Log.e("test", "test");

    byte[] leafCert = CertTestUtil.pemToDer(
        "test/java/org/chromium/net/testing/data/ok_cert_by_intermediate.pem");
    byte[] intermediateCert =
        CertTestUtil.pemToDer("test/java/org/chromium/net/testing/data/intermediate_ca_cert.pem");

    byte[][] certificateChain = new byte[][] {leafCert, intermediateCert, rootCert};
    // byte[][] certificateChain = new byte[][]{rootCert, intermediateCert, leafCert};
    AndroidCertVerifyResult result =
        AndroidNetworkLibrary.verifyServerCertificates(certificateChain, "RSA", "www.example.test");

    assertNotNull(result);
    assertFalse(result.isIssuedByKnownRoot());
    assertEquals(result.getStatus(), CertVerifyStatusAndroid.FAILED);

    //        Assert.assertFalse(X509Util.verifyKeyUsage(X509Util.createCertificateFromBytes(
    //                CertTestUtil.pemToDer(CERTS_DIRECTORY + CRITICAL_CODE_SIGNING_EE))));

    //        Assert.assertFalse(X509Util.verifyKeyUsage(X509Util.createCertificateFromBytes(
    //                CertTestUtil.pemToDer(CERTS_DIRECTORY + NON_CRITICAL_CODE_SIGNING_EE))));

    //        Assert.assertFalse(X509Util.verifyKeyUsage(X509Util.createCertificateFromBytes(
    //                readFileBytes(CERTS_DIRECTORY + WEB_CLIENT_AUTH_EE))));

    //        Assert.assertTrue(X509Util.verifyKeyUsage(X509Util.createCertificateFromBytes(
    //                CertTestUtil.pemToDer(CERTS_DIRECTORY + OK_CERT))));
  }
}
