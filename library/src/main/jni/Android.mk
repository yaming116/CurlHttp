LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

#LOCAL_C_INCLUDES += $(LOCAL_PATH)/curl


LOCAL_MODULE := curl4java
LOCAL_MODULE_FILENAME := libcurl4java
LOCAL_SRC_FILES := curlandroid.cpp

LOCAL_LDFLAGS := $(LOCAL_PATH)/lib/$(TARGET_ARCH_ABI)/libcurl.a
LOCAL_STATIC_LIBRARIES := static_android

LOCAL_LDLIBS += -lm -llog
include $(BUILD_SHARED_LIBRARY)