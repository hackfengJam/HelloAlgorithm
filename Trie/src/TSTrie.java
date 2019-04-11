
public class TSTrie { //<C>
    /*
    *
    *
    *
    * 三向单词查找树
        命题4：
        描述：有N个平均长度为w的字符串构造的三向单词查找树中的链接总数在3N到3Nw之间。
        证明：同命题3。
        备注：三向单词查找树实际使用的内存空间一般都低于由每个字符三个链接得到的上界，因为有相同前缀的键会共享树种的高层结点。
    * */
    private class Node {
        public char c;
        public int val;
        public Node left, mid, right;

        public Node(char c, int val, Node left, Node mid, Node right) {
            this.c = c;
            this.val = val;
            this.left = left;
            this.mid = mid;
            this.right = right;
        }

        public Node(char c) {
            this(c, -1, null, null, null);
        }

        public Node() {
            this('\0', -1, null, null, null);
        }
    }

    private Node root;
    private int size;


    public TSTrie() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    private Node add(Node cur, String key, int val, int d) {
        char c = key.charAt(d);
        if (cur == null) {
            cur = new Node(c);
        }
        if (cur.c > c) {
            cur.left = add(cur.left, key, val, d);
        } else if (cur.c < c) {
            cur.right = add(cur.right, key, val, d);
        } else if (d < key.length() - 1) {
            cur.mid = add(cur.mid, key, val, d + 1);
        } else {
            cur.val = val;
        }
        return cur;
    }

    public void add(String key, int val) {
        root = add(root, key, val, 0);
    }

    public int get(String key) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (cur == null) {
                return -1;
            }
            if (c < cur.c) {
                cur = cur.left;
                i--;
            } else if (c > cur.c) {
                cur = cur.right;
                i--;
            } else {
                if (i == key.length() - 1)
                    return cur.val;
                cur = cur.mid;
            }
        }
        return cur.val;
    }

    public static void main(String[] args) {
        TSTrie tsTrie = new TSTrie();
        tsTrie.add("apple", 1);
        tsTrie.add("applc", 2);
        System.out.println(tsTrie.get("app"));
        System.out.println(tsTrie.get("apple"));
        System.out.println(tsTrie.get("applc"));
        System.out.println(tsTrie.get("appld"));
        System.out.println(tsTrie.get("appldd"));
    }
}
