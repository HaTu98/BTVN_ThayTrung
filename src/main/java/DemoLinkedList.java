import java.util.Iterator;
import java.util.LinkedList;

public class DemoLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        LinkedList<Integer> list = new LinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.poll();
        System.out.println(linkedList.size());

        int data = linkedList.poll();
        int tmp;
//        Iterator ite = linkedList.iterator();
//
//       while (true){
//           if(linkedList.iterator().hasNext()) {
//               tmp = linkedList.peek();
//               if (data == tmp) {
//                   linkedList.poll();
//               } else {
//                   list.add(data);
//                   data = tmp;
//               }
//           } else {
//               break;
//           }
//       }
//
//       for(int i = 0 ; i  < list.size(); i++){
//           System.out.println(list.get(i));
//       }

        while (linkedList.iterator().hasNext()){
            int i = linkedList.iterator().next();
            System.out.println(i);
        }

    }
}
