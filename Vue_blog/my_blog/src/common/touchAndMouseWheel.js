import eventHandle from "./event";

export default {
  mouseHandle(event) {
    event = eventHandle.getEvent(event);
    let delta = eventHandle.getWheelDelta(event);
    let scrollPosition = document.documentElement.scrollTop ?
      document.documentElement.scrollTop :
      document.body.scrollTop;
    if (delta < 0) {
      // 向下滚动
      if ("flex" == $(".fixed.menu").css("display")) {
        $(".fixed.menu").transition("fade out");
      }
    } else if (delta > 0) {
      // 向上滚动
      if (
        "" == $(".fixed.menu").css("display") ||
        "flex" != $(".fixed.menu").css("display")
      ) {
        $(".fixed.menu").transition("fade in");
      }
    }
  },
  touchMove(event) {
    event = eventHandle.getEvent(event);
    let touchY = eventHandle.getTouchY(event);
    if (touchY.clientY - this.touchStartPosition > 0) {
      // 向上滑动
      if ("flex" == $(".fixed.menu").css("display")) {
        $(".fixed.menu").transition("fade out");
      }
    } else {
      // 向下滑动
      if (
        "" == $(".fixed.menu").css("display") ||
        "flex" != $(".fixed.menu").css("display")
      ) {
        $(".fixed.menu").transition("fade in");
      }
    }
    this.touchStartPosition = touchY.clientY;
  },
  touchStart(event) {
    event = eventHandle.getEvent(event)
    this.touchStartPosition = eventHandle.getTouchY(event).clientY
  }
}