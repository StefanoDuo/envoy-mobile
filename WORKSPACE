workspace(name = "envoy_mobile")

# The pgv imports require gazelle to be available early on.
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
http_archive(
    name = "bazel_gazelle",
    sha256 = "de69a09dc70417580aabf20a28619bb3ef60d038470c7cf8442fafcf627c21cb",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.24.0/bazel-gazelle-v0.24.0.tar.gz",
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.24.0/bazel-gazelle-v0.24.0.tar.gz",
    ],
)

load("@envoy_mobile//bazel:envoy_mobile_repositories.bzl", "envoy_mobile_repositories")
envoy_mobile_repositories()

local_repository(
    name = "envoy",
    path = "envoy",
)

local_repository(
    name = "envoy_build_config",
    path = "envoy_build_config",
)

load("@envoy//bazel:api_binding.bzl", "envoy_api_binding")
envoy_api_binding()

load("@envoy//bazel:api_repositories.bzl", "envoy_api_dependencies")
envoy_api_dependencies()

load("@envoy//bazel:repositories.bzl", "envoy_dependencies")
envoy_dependencies()

load("@envoy//bazel:repositories_extra.bzl", "envoy_dependencies_extra")
envoy_dependencies_extra()

load("@envoy//bazel:dependency_imports.bzl", "envoy_dependency_imports")
envoy_dependency_imports()

load("@envoy_mobile//bazel:envoy_mobile_dependencies.bzl", "envoy_mobile_dependencies")
envoy_mobile_dependencies()

load("@envoy_mobile//bazel:envoy_mobile_toolchains.bzl", "envoy_mobile_toolchains")
envoy_mobile_toolchains()

load("@pybind11_bazel//:python_configure.bzl", "python_configure")
python_configure(name = "local_config_python", python_version = "3")

load("//bazel:python.bzl", "declare_python_abi")
declare_python_abi(name = "python_abi", python_version = "3")

load("//bazel:android_configure.bzl", "android_configure")
android_configure(
    name = "local_config_android",
    sdk_api_level = 30,
    ndk_api_level = 21,
    build_tools_version = "30.0.3"
)

load("@local_config_android//:android_configure.bzl", "android_workspace")
android_workspace()

load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")
protobuf_deps()

#ATS_COMMIT = "149d38c6feb19b0e505cb4ea014c35795e069e45"
local_repository(
    name = "android_test_support",
    #strip_prefix = "android-test-%s" % ATS_COMMIT,
    #urls = ["https://github.com/android/android-test/archive/%s.tar.gz" % ATS_COMMIT],
    path = "/usr/local/google/home/stefanoduo/repos/android-test",
)
load("@android_test_support//:repo.bzl", "android_test_repositories")
android_test_repositories()

new_local_repository(
  name = "emulator",
  path = "/usr/local/google/home/stefanoduo/Android/Sdk/system-images/android-26/google_apis_playstore/x86/",
  build_file = "emulator.BUILD",
)
