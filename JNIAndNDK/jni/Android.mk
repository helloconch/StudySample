LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := abc
LOCAL_SRC_FILES := abc.cpp

include $(BUILD_SHARED_LIBRARY)
