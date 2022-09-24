import java.io.*;
import java.util.*;

public class Main {
  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack<Node> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }

  public static int size(Node node) {
    int s = 0;

    for (Node child : node.children) {
      s += size(child);
    }
    s += 1;

    return s;
  }

  public static void levelOrderLinewiseZZ(Node node) {
    Stack< Node> ms = new Stack< >();
    ms.push(node);

    Stack< Node> cs = new Stack< >();
    int level = 1;

    while (ms.size() > 0) {
      node = ms.pop();
      System.out.print(node.data + " ");
      if (level % 2 == 1) {
        for (int i = 0; i < node.children.size(); i++) {
          cs.push(node.children.get(i));
        }
      } else {
        for (int i = node.children.size() - 1; i >= 0; i--) {
          cs.push(node.children.get(i));
        }
      }

      if (ms.size() == 0) {
        ms = cs;
        cs = new Stack< >();
        level++;
        System.out.println();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    Node root = construct(arr);
    levelOrderLinewiseZZ(root);
  }

}
