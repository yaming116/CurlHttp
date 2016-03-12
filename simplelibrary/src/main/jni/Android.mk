LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_C_INCLUDES += $(LOCAL_PATH)/curl


LOCAL_MODULE := curlsimaplehttp
LOCAL_MODULE_FILENAME := libcurlsimaplehttp
LOCAL_SRC_FILES := curlhttpjni.cpp

#LOCAL_LDFLAGS += $(LOCAL_PATH)/libcurl.a
LOCAL_LDFLAGS := $(LOCAL_PATH)/lib/$(TARGET_ARCH_ABI)/libcurl.a

LOCAL_LDLIBS += -lm -llog
include $(BUILD_SHARED_LIBRARY)