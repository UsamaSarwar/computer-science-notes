function animationFunc(t) {
  var n = $(window).width(),
    i = $(window).height();
  window.innerWidth;
  n > 767
    ? ((jQuery.fn.isOnScreen = function() {
        var t = $(window),
          n = { top: t.scrollTop(), left: t.scrollLeft() };
        (n.right = n.left + t.width()), (n.bottom = n.top + t.height());
        var i = this.offset();
        return (
          (i.right = i.left + this.outerWidth()),
          (i.bottom = i.top + this.outerHeight()),
          !(
            n.right < i.left ||
            n.left > i.right ||
            n.bottom < i.top ||
            n.top > i.bottom
          )
        );
      }),
      jQuery(".animateElement").each(function() {
        var t = $(this),
          n = parseInt(t.data("animation-delay")) / 1e3,
          a = t.data("animation-type");
        t.isOnScreen() &&
          (t.hasClass("animated") ||
            t
              .addClass("animated")
              .addClass(a)
              .trigger("animateIn"),
          t.css({
            "-webkit-animation-delay": n + "s",
            "animation-delay": n + "s"
          })),
          $(window).scroll(function() {
            var e = t.offset().top,
              o = t.outerHeight() + e,
              s = $(this).scrollTop();
            if (s > (e = e - i) && s < o) {
              t.hasClass("animated") ||
                t
                  .addClass("animated")
                  .addClass(a)
                  .trigger("animateIn"),
                t.css({
                  "-webkit-animation-delay": n + "s",
                  "animation-delay": n + "s"
                });
              jQuery(".animated").one(
                "webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend",
                function() {
                  $(this).addClass("fill-mode-none");
                }
              );
            }
          });
      }))
    : jQuery(".animateElement").each(function() {
        jQuery(this).removeClass("animateElement");
      });
}
$(document).ready(function(t) {
  animationFunc();
}),
  $(window).resize(function(t) {
    animationFunc();
  });
