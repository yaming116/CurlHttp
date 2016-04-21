LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := curlandroid
LOCAL_SRC_FILES := curlandroid.cpp
LOCAL_LDLIBS    := -lm -llog # -ljnigraphics
# shared library
LOCAL_C_INCLUDES := $(LOCAL_PATH) $(LOCAL_PATH)/shared/curl/include
LOCAL_SHARED_LIBRARIES := zhitongyuninterfaces
include $(BUILD_SHARED_LIBRARY)

#LOCAL_STATIC_LIBRARIES := zhitongyuninterfaces
#include $(BUILD_SHARED_LIBRARY)


# Add prebuilt libcurl
include $(CLEAR_VARS)

LOCAL_MODULE := zhitongyuninterfaces
LOCAL_SRC_FILES := $(TARGET_ARCH_ABI)/libzhitongyuninterfaces.so

include $(PREBUILT_SHARED_LIBRARY)
