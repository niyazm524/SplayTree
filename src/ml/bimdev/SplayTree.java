package ml.bimdev;

public class SplayTree<K extends Comparable<K>, V> {

    private Node rootNode;   

   
    public boolean exists(K chave) {
        return (search(chave) != null);
    }

  
    public int depth() {
        return depth(rootNode);
    }

  
    private int depth(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(depth(x.leftNode), depth(x.rightNode));
    }

   
    public int size() {
        return size(rootNode);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return (1 + size(x.leftNode) + size(x.rightNode));
        }
    }

   
    private Node rightTurn(Node node) {
        Node x = node.leftNode;
        node.leftNode = x.rightNode;
        x.rightNode = node;
        return x;
    }

 
    private Node leftTurn(Node node) {
        Node x = node.rightNode;
        node.rightNode = x.leftNode;
        x.leftNode = node;
        return x;
    }

    
    private Node splay(Node node, K key) {
        
        if (node == null) {
            return null;
        }

        
        int cmp1 = key.compareTo(node.key);

        if (cmp1 < 0) {
            if (node.leftNode == null) {
                
                return node;
            }
            int cmp2 = key.compareTo(node.leftNode.key);
            if (cmp2 < 0) {
                
                node.leftNode.leftNode = splay(node.leftNode.leftNode, key);
                node = rightTurn(node);
            } else if (cmp2 > 0) {
                
                node.leftNode.rightNode = splay(node.leftNode.rightNode, key);
                if (node.leftNode.rightNode != null) {
                    node.leftNode = leftTurn(node.leftNode);
                }
            }
            if (node.leftNode == null) {
                return node;
            } else {
                return rightTurn(node);
            }
        } else if (cmp1 > 0) {
            if (node.rightNode == null) {
                
                return node;
            }

            int cmp2 = key.compareTo(node.rightNode.key);
            if (cmp2 < 0) {
                
                node.rightNode.leftNode = splay(node.rightNode.leftNode, key);
                if (node.rightNode.leftNode != null) {
                    node.rightNode = rightTurn(node.rightNode);
                }
            } else if (cmp2 > 0) {
                
                node.rightNode.rightNode = splay(node.rightNode.rightNode, key);
                node = leftTurn(node);
            }

            if (node.rightNode == null) {
                return node;
            } else {
                return leftTurn(node);
            }
        } else {
            return node;
        }
    }

  
    public V search(K key) {
        rootNode = splay(rootNode, key);
        int comparacao = key.compareTo(rootNode.key);
        if (comparacao == 0) {
            return rootNode.value;
        } else {
            return null;
        }
    }

    
    public void insert(K key, V value) {
    
        if (rootNode == null) {
            rootNode = new Node(key, value);
            return;
        }

   
        rootNode = splay(rootNode, key);

        
        int cmp = key.compareTo(rootNode.key);
        if (cmp < 0) {
            
            Node n = new Node(key, value);
            n.leftNode = rootNode.leftNode;
            n.rightNode = rootNode;
            rootNode.leftNode = null;
            rootNode = n;
        } else if (cmp > 0) {
            Node n = new Node(key, value);
            n.rightNode = rootNode.rightNode;
            n.leftNode = rootNode;
            rootNode.rightNode = null;
            rootNode = n;
        } else if (cmp == 0) {
            
            rootNode.value = value;
        }

    }

  
    public void remove(K key) {
        
        if (rootNode == null) {
            return;
        }

    
        rootNode = splay(rootNode, key);
        int cmp = key.compareTo(rootNode.key);

        if (cmp == 0) {
            if (rootNode.leftNode == null) {
                rootNode = rootNode.rightNode;
            } else {
                Node x = rootNode.rightNode;
                rootNode = rootNode.leftNode;
                splay(rootNode, key);
                rootNode.rightNode = x;
            }
        }
        
    }

    
    private class Node {

        private K key;       
        private V value;       
        private Node leftNode;   
        private Node rightNode;    


   
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}