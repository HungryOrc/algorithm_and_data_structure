/* 函数 private int unreliableJump() 能操纵小公仔向上跳一格或向下跳一格。跳的过程我们不用管。跳了以后会返回一个int。
如果刚才是向上跳了，则返回 1，如果是向下跳了，则返回 -1.
要求基于这个函数，写一个函数 public void reliableJump()，要达到的效果是，只要运行一次 reliableJump，就一定会向上跳且仅跳一步 */

// 思路：关键在于，如果往下跳了一步，则一定要往上再跳两步才能弥补并实现目标 ！！！
// Ref: http://www.1point3acres.com/bbs/thread-278412-1-1.html

public void reliableJump() {
    if (unreliableJump() == 1) {
        return;
    } else {
        reliableJump();
        reliableJump();
        // 如果这两个reliableJump里的任何一个又往下跳了，则它又需要额外的两步向上找补回来。这个机制靠这里的recursion自动实现了 ！！！
    }
}
