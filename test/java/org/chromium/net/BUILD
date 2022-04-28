load("@envoy//bazel:envoy_build_system.bzl", "envoy_package")
load("@envoy_mobile//bazel:kotlin_test.bzl", "envoy_mobile_android_test")

licenses(["notice"])  # Apache 2

envoy_package()

envoy_mobile_android_test(
    name = "net_tests",
    srcs = [
        "BrotliTest.java",
        "CronetEngineBuilderTest.java",
        "CronetStressTest.java",
        "DiskStorageTest.java",
        "GetStatusTest.java",
        "RequestFinishedInfoTest.java",
        "UploadDataProvidersTest.java",
        "UrlResponseInfoTest.java",
    ],
    exec_properties = {
        # TODO(lfpino): Remove this once the sandboxNetwork=off works for ipv4 localhost addresses.
        "sandboxNetwork": "standard",
    },
    native_deps = [
        "//library/common/jni:libndk_envoy_jni.so",
        "//library/common/jni:libndk_envoy_jni.jnilib",
    ],
    deps = [
        "//library/java/org/chromium/net",
        "//library/java/org/chromium/net/impl:cronvoy",
        #        "//library/java/io/envoyproxy/envoymobile/engine:envoy_base_engine_lib",
        #        "//library/java/io/envoyproxy/envoymobile/engine:envoy_engine_lib",
        #        "//library/kotlin/io/envoyproxy/envoymobile:envoy_interfaces_lib",
        #        "//library/kotlin/io/envoyproxy/envoymobile:envoy_lib",
        "//test/java/org/chromium/net/testing",
    ],
)

android_device(
    name = "mydevice",
    cache = 32,
    #    default_properties = "nexus_s.properties",
    #    default_properties = "@android_test_support//tools/android/emulated_devices/generic_phone:google_30_x86",
    horizontal_resolution = 480,
    ram = 512,
    screen_density = 233,
    #    system_image = "@emulator//:images",
    system_image = "//test/java/org/chromium/net/testing:emulator_images",
    #    system_image = "@android_test_support//tools/android/emulated_devices/nexus_s:nexus_s",
    vertical_resolution = 800,
    vm_heap = 32,
)

android_binary(
    name = "app_test",
    testonly = 1,
    srcs = [
        "AndroidNetworkLibraryTest.java",
        "CertTestUtil.java",
        "X509UtilTest.java",
    ],
    instruments = ":app",
    manifest = "AndroidManifest.xml",
    deps = [
        "//test/java/org/chromium/net/testing",
        "//library/java/org/chromium/net",
        "//library/java/org/chromium/net/impl:cronvoy",
        #        "@maven//:androidx_annotation_annotation",
        #        "@maven//:androidx_test_monitor",
        #        "@maven//:junit_junit",
        "//library/java/io/envoyproxy/envoymobile/engine:envoy_base_engine_lib",
        "//library/java/io/envoyproxy/envoymobile/engine:envoy_engine_lib",
        "//library/kotlin/io/envoyproxy/envoymobile:envoy_interfaces_lib",
        "//library/kotlin/io/envoyproxy/envoymobile:envoy_lib",
        "@maven//:androidx_annotation_annotation",
        "@maven//:androidx_test_core",
        "@maven//:androidx_test_ext_junit",
        "@maven//:androidx_test_runner",
        "@maven//:androidx_test_monitor",
        "@maven//:androidx_test_rules",
        #            "@maven//:org_robolectric_robolectric",
        #            "@robolectric//bazel:android-all",
        "@maven//:org_assertj_assertj_core",
        "@maven//:junit_junit",
        "@maven//:org_mockito_mockito_inline",
        "@maven//:org_mockito_mockito_core",
        "@maven//:com_squareup_okhttp3_okhttp",
        "@maven//:com_squareup_okhttp3_mockwebserver",
        "@maven//:com_squareup_okio_okio",
        "@maven//:org_hamcrest_hamcrest",
        "@maven//:com_google_truth_truth",
    ],
)

android_library(
    name = "app_library",
    testonly = 1,
    manifest = "AndroidManifest.xml",
    deps = [
        "//library/java/io/envoyproxy/envoymobile/engine:envoy_base_engine_lib",
        "//library/java/io/envoyproxy/envoymobile/engine:envoy_engine_lib",
        "//library/java/org/chromium/net",
        "//library/java/org/chromium/net/impl:cronvoy",
        "//library/kotlin/io/envoyproxy/envoymobile:envoy_interfaces_lib",
        "//library/kotlin/io/envoyproxy/envoymobile:envoy_lib",
    ],
)

android_binary(
    name = "app",
    testonly = 1,
    manifest = "AndroidManifest.xml",
    deps = [
        ":app_library",
    ],
)

android_instrumentation_test(
    name = "android_library_tests",
    data = [
        "//library/common/jni:libndk_envoy_jni.jnilib",
        "//library/common/jni:libndk_envoy_jni.so",
    ],
    #    target_device = ":mydevice",
    target_device = "@android_test_support//tools/android/emulated_devices/generic_phone:google_28_x86",
    test_app = ":app_test",
)

envoy_mobile_android_test(
    name = "cronet_url_request_context_test",
    srcs = [
        "CronetUrlRequestContextTest.java",
    ],
    exec_properties = {
        # TODO(lfpino): Remove this once the sandboxNetwork=off works for ipv4 localhost addresses.
        "sandboxNetwork": "standard",
    },
    native_deps = [
        "//library/common/jni:libndk_envoy_jni.so",
        "//library/common/jni:libndk_envoy_jni.jnilib",
    ],
    deps = [
        "//library/java/io/envoyproxy/envoymobile/engine:envoy_base_engine_lib",
        "//library/java/io/envoyproxy/envoymobile/engine:envoy_engine_lib",
        "//library/java/org/chromium/net",
        "//library/java/org/chromium/net/impl:cronvoy",
        "//library/kotlin/io/envoyproxy/envoymobile:envoy_interfaces_lib",
        "//library/kotlin/io/envoyproxy/envoymobile:envoy_lib",
        "//test/java/org/chromium/net/testing",
    ],
)

envoy_mobile_android_test(
    name = "cronet_url_request_test",
    srcs = [
        "CronetUrlRequestTest.java",
    ],
    exec_properties = {
        # TODO(lfpino): Remove this once the sandboxNetwork=off works for ipv4 localhost addresses.
        "sandboxNetwork": "standard",
    },
    native_deps = [
        "//library/common/jni:libndk_envoy_jni.so",
        "//library/common/jni:libndk_envoy_jni.jnilib",
    ],
    deps = [
        "//library/java/io/envoyproxy/envoymobile/engine:envoy_base_engine_lib",
        "//library/java/io/envoyproxy/envoymobile/engine:envoy_engine_lib",
        "//library/java/org/chromium/net",
        "//library/java/org/chromium/net/impl:cronvoy",
        "//library/kotlin/io/envoyproxy/envoymobile:envoy_interfaces_lib",
        "//library/kotlin/io/envoyproxy/envoymobile:envoy_lib",
        "//test/java/org/chromium/net/testing",
    ],
)
