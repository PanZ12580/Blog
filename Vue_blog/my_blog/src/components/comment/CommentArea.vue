<template>
  <div id="comment_area" class="ui teal segment m-bg">
    <div class="ui threaded comments" style="max-width: none !important;">
      <h3 class="ui dividing header">Comments</h3>
      <div class="comment">
        <a href="#" class="avatar">
          <img src="~assets/images/59558867.jpg" />
        </a>
        <div class="content">
          <a href="#" class="author">Matt</a>
          <div class="metadata">
            <span class="date">今天下午 5:42</span>
          </div>
          <div class="text">太赞了！</div>
          <div class="actions">
            <a href="#" class="reply m_reply">回复</a>
          </div>
        </div>
      </div>
      <div class="comment">
        <a href="#" class="avatar">
          <img src="~assets/images/59558867.jpg" />
        </a>
        <div class="content">
          <a href="#" class="author">Elliot Fu</a>
          <div class="metadata">
            <span class="date">昨天上午12:30</span>
          </div>
          <div class="text">
            <p>這對我的研究是非常有用.謝謝!</p>
          </div>
          <div class="actions">
            <a href="#" class="reply m_reply">回复</a>
          </div>
        </div>
        <div class="comments">
          <div class="comment">
            <a href="#" class="avatar">
              <img src="~assets/images/59558867.jpg" />
            </a>
            <div class="content">
              <a href="#" class="author">Jenny Hess</a>
              <div class="metadata">
                <span class="date">刚刚</span>
              </div>
              <div class="text">艾略特你永远是多么正确 :)</div>
              <div class="actions">
                <a href="#" class="reply m_reply">回复</a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="comment">
        <a href="#" class="avatar">
          <img src="~assets/images/59558867.jpg" />
        </a>
        <div class="content">
          <a href="#" class="author">Joe Henderson</a>
          <div class="metadata">
            <span class="date">5 天以前</span>
          </div>
          <div class="text">老兄，这太棒了。非常感谢。</div>
          <div class="actions">
            <a href="#" class="reply m_reply">回复</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'CommentArea'
};
</script>

<style>
</style>