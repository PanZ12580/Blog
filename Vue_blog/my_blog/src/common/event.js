export default {
  getEvent: function(event) {
    return event || window.event;
  },
  addEvent: function(element, type, handler) {
    if (element.addEventListener) {
      element.addEventListener(type, handler, false);
    } else if (element.attachEvent) {
      element.attachEvent("on" + type, handler);
    } else {
      element["on" + type] = handler;
    }
  },
  getWheelDelta: function(event) {
    return event.wheelDelta ? event.wheelDelta : -event.detail * 40;
  },
  getTouchY: function(event) {
    return event.touches[0]
  },
  removeEvent: function(element, type, handler) {
    if (element.removeEventListener) {
      element.removeEventListener(type, handler);
    } else if (element.attachEvent) {
      element.detachEvent('on' + type);
    } else {
      delete element["on" + type];
    }
  }
}