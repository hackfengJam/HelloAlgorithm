package weight;

public class UnionFind {
    // 并查集 - 基于路径压缩

    private int[] parent;
    private int[] rank; // rank[i] 表示以i为根的集合所表示的树的层数

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int getSize() {
        return parent.length;
    }

    // 查找过程，查找元素p对应的集合编号
    // O(h)复杂度，h为树的高度
    private int find(int p) {

        if (p < 0 && p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }

        while (p != parent[p]) {
            parent[p] = parent[parent[p]];// 路径压缩
            p = parent[p];
        }
        return p;
    }

    // 查看元素p和元素q是否所属一个集合
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h) 复杂度，h为树的高度
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        // 基于 size 优化 quick union
        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到元素个数多的集合上
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;

        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else { // rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}