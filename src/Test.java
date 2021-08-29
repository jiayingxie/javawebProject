import java.util.*;

public class Test {

    class LRUCache {
        int capacity;
        Map<Integer, Integer> map;
        Queue<Integer> queue;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            queue = new LinkedList<>();
        }

        public int get(int key) {
            if(!map.containsKey(key)) return -1;
            queue.add(key);
            // 确保queue至多有capacity个元素
            handleLRU();
            return map.get(key);
        }

        public void put(int key, int value) {
            if(map.containsKey(key)) {
                map.put(key, value);
            } else {
                if(queue.size()==capacity){
                    int removedKey = queue.remove();
                    map.remove(removedKey);
                }
                map.put(key, value);
                capacity++;
            }
            queue.add(key);
            handleLRU();
        }

        private void handleLRU(){
            while(queue.size()>capacity){
                queue.remove();
            }
        }
    }

    public static void main(String[] args) {
//        class obj
        System.out.println("this is a test");

    }
}
