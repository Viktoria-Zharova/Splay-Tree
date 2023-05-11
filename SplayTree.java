class SplayTree {

    private Node root;
    int counter;
    // Класс для представления узла дерева
    static class Node {
        int key;
        Node left, right, parent;

        Node(int key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
    }

    // Вспомогательный метод для поворота узла влево
    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Вспомогательный метод для поворота узла вправо
    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Метод для выполнения splay операции
    private void splay(Node x) {
        while (x.parent != null) {
            if (x.parent.parent == null) {
                if (x == x.parent.left) {
                    rotateRight(x.parent);
                } else {
                    rotateLeft(x.parent);
                }
            } else if (x == x.parent.left && x.parent == x.parent.parent.left) {
                rotateRight(x.parent.parent);
                rotateRight(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.right) {
                rotateLeft(x.parent.parent);
                rotateLeft(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.left) {
                rotateLeft(x.parent);
                rotateRight(x.parent);
            } else {
                rotateRight(x.parent);
                rotateLeft(x.parent);
            }
        }
    }

    // Метод для поиска ключа в дереве
    public boolean contains(int key) {
        counter = 0;
        counter++;
        return search(key) != null;
    }

    // Метод для поиска узла по ключу
    public Node search(int key) {
        counter = 0;
        Node x = root;
        while (x != null) {
            counter++;
            if (key < x.key) {
                x = x.left;
            } else if (key > x.key) {
                x = x.right;
            } else {
                return x;
            }
        }
        return null;
    }

    // Метод для добавления узла в дерево
    public void insert(int key) {
        counter = 0;
        if (root == null) {
            root = new Node(key, null);
            return;
        }
        Node x = root;
        Node parent = null;
        while (x != null) {
            counter++;
            parent = x;
            if (key < x.key) {
                x = x.left;
            } else if (key > x.key) {
                x = x.right;
            } else {
                return; // Узел уже существует, не добавляем его еще раз
            }
        }
        Node node = new Node(key, parent);
        if (key < parent.key) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        splay(node); // Выполняем splay операцию для нового узла
    }

    // Метод для удаления узла из дерева
    public void remove(int key) {
        counter = 0;
        Node node = search(key);
        if (node == null) {
            return; // Узел не найден, нечего удалять
        }
        splay(node); // Выполняем splay операцию для узла, который хотим удалить
        if (node.left == null) {
            counter++;
            transplant(node, node.right);
        } else if (node.right == null) {
            counter++;
            transplant(node, node.left);
        } else {
            Node minRight = minimum(node.right);
            if (minRight.parent != node) {
                counter++;
                transplant(minRight, minRight.right);
                minRight.right = node.right;
                minRight.right.parent = minRight;
            }
            counter++;
            transplant(node, minRight);
            minRight.left = node.left;
            minRight.left.parent = minRight;
        }
    }

    // Вспомогательный метод для замены узла u на узел v
    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    // Вспомогательный метод для поиска узла с минимальным ключом в поддереве с корнем в x
    private Node minimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }
}
