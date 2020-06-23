import ToolBar from "components/toolbar/ToolBar";

import eventHandle from "./event";
import touchAndMouseWheel from "./touchAndMouseWheel";
import {
  debounce
} from './utils'

// 监听页面滚动，控制导航栏的显示与隐藏
export const scrollEventListener = {
  data() {
    return {
      // 监听滚轮及手机触摸滑动事件
      mouseHandle: debounce(touchAndMouseWheel.mouseHandle),
      touchMove: debounce(touchAndMouseWheel.touchMove),
      touchStart: debounce(touchAndMouseWheel.touchStart),
    }
  },
  activated() {
    /**
     * 监听滚轮及手机触摸
     */
    eventHandle.addEvent(document, "mousewheel", this.mouseHandle);
    eventHandle.addEvent(document, "DOMMouseScroll", this.mouseHandle);
    eventHandle.addEvent(document, "touchstart", this.touchStart);
    eventHandle.addEvent(document, "touchmove", this.touchMove);
  },
  deactivated() {
    /**
     * 移除监听
     */
    eventHandle.removeEvent(document, "mousewheel", this.mouseHandle);
    eventHandle.removeEvent(document, "DOMMouseScroll", this.mouseHandle);
    eventHandle.removeEvent(document, "touchstart", this.touchStart);
    eventHandle.removeEvent(document, "touchmove", this.touchMove);
  }
}

// 工具条
export const toolbarControl = {
  components: {
    ToolBar
  },
  activated() {
    /**
     * 控制工具条显示与隐藏
     */
    const toolBar = this.$refs.toolBar.$el
    const header = this.$refs.header.$el
    $(header).visibility({
      once: false,
      onTopPassed() {
        $(toolBar).transition("fade in");
      },
      onTopPassedReverse() {
        $(toolBar).transition("zoom out");
      }
    });
  }
}

// ------------------------------------非Keep-Alive版本---------------------------------------

// 监听页面滚动，控制导航栏的显示与隐藏
export const NoAliveScrollEventListener = {
  data() {
    return {
      // 监听滚轮及手机触摸滑动事件
      mouseHandle: debounce(touchAndMouseWheel.mouseHandle),
      touchMove: debounce(touchAndMouseWheel.touchMove),
      touchStart: debounce(touchAndMouseWheel.touchStart),
    }
  },
  mounted() {
    /**
     * 监听滚轮及手机触摸
     */
    eventHandle.addEvent(document, "mousewheel", this.mouseHandle);
    eventHandle.addEvent(document, "DOMMouseScroll", this.mouseHandle);
    eventHandle.addEvent(document, "touchstart", this.touchStart);
    eventHandle.addEvent(document, "touchmove", this.touchMove);
  },
  beforeDestroy() {
    /**
     * 移除监听
     */
    eventHandle.removeEvent(document, "mousewheel", this.mouseHandle);
    eventHandle.removeEvent(document, "DOMMouseScroll", this.mouseHandle);
    eventHandle.removeEvent(document, "touchstart", this.touchStart);
    eventHandle.removeEvent(document, "touchmove", this.touchMove);
  }
}

// 工具条
export const NoAliveToolbarControl = {
  components: {
    ToolBar
  },
  mounted() {
    /**
     * 控制工具条显示与隐藏
     */
    const toolBar = this.$refs.toolBar.$el
    const header = this.$refs.header.$el
    $(header).visibility({
      once: false,
      onTopPassed() {
        $(toolBar).transition("fade in");
      },
      onTopPassedReverse() {
        $(toolBar).transition("zoom out");
      }
    });
  }
}