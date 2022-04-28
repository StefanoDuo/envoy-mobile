package org.chromium.net;

import static org.chromium.net.testing.CronetTestRule.getContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import android.os.ConditionVariable;
import android.util.Log;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
// import com.google.android.collect.Sets;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.net.impl.CronetMetrics;
import org.chromium.net.testing.CronetTestRule;
import org.chromium.net.testing.CronetTestRule.CronetTestFramework;
import org.chromium.net.testing.CronetTestRule.OnlyRunNativeCronet;
import org.chromium.net.testing.CronetTestRule.RequiresMinApi;
import org.chromium.net.testing.Feature;
import org.chromium.net.testing.MetricsTestUtil;
import org.chromium.net.testing.MetricsTestUtil.TestExecutor;
import org.chromium.net.testing.MetricsTestUtil.TestRequestFinishedListener;
import org.chromium.net.testing.NativeTestServer;
import org.chromium.net.testing.TestUrlRequestCallback;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test AndroidNetworkLibrary.verifyServerCertificates.
 */
@RunWith(AndroidJUnit4.class)
public class AndroidNetworkLibraryTest {
  @Rule public final CronetTestRule mTestRule = new CronetTestRule();

  @Before
  public void setUp() throws Exception {
    NativeTestServer.startNativeTestServer(getContext());
    //    mUrl = NativeTestServer.getEchoBodyURL();
    //    mTestFramework = mTestRule.startCronetTestFramework();
  }

  @After
  public void tearDown() throws Exception {
    NativeTestServer.shutdownNativeTestServer();
  }

  @Test
  @SmallTest
  public void testRequestFinishedListener() throws Exception {
    AndroidCertVerifyResult result =
        AndroidNetworkLibrary.verifyServerCertificates(null, "RSA", "www.example.test");
    assertNotNull(result);
    assertFalse(result.isIssuedByKnownRoot());
    assertEquals(result.getStatus(), CertVerifyStatusAndroid.FAILED);

    File file = new File("test/java/org/chromium/net/testing/data/root_ca_cert.pem");
    byte[] cert = Files.readAllBytes(file.toPath());
    byte[][] certs = new byte[][] {cert};
    try {
      AndroidNetworkLibrary.addTestRootCertificate(cert);
    } catch (Exception e) {
      Log.e("test", e.toString());
      //      fail();
    }
  }
}
