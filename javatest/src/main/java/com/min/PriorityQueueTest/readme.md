# PriorityQueue优先级队列

## 简介

类 PriorityQueue
java.lang.Object 
——继承者 java.util.AbstractCollection 
———– 继承者 java.util.AbstractQueue 
—————–继承者 java.util.PriorityQueue

类型参数： 
E - collection 中所保存元素的类型。 
所有已实现的接口： 
Serializable, Iterable, Collection, Queue

public class PriorityQueue<E>
    extends AbstractQueue<E>
    implements Serializable

一个基于优先级堆的无界优先级队列。优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序，具体取决于所使用的构造方法。优先级队列不允许使用 null 元素。依靠自然顺序的优先级队列还不允许插入不可比较的对象（这样做可能导致 ClassCastException）。

此队列的头 是按指定排序方式确定的最小 元素。如果多个元素都是最小值，则头是其中一个元素——选择方法是任意的。队列获取操作 poll、remove、peek 和 element 访问处于队列头的元素。

优先级队列是无界的，但是有一个内部容量，控制着用于存储队列元素的数组大小。它通常至少等于队列的大小。随着不断向优先级队列添加元素，其容量会自动增加。无需指定容量增加策略的细节。

此类及其迭代器实现了 Collection 和 Iterator 接口的所有可选 方法。方法 iterator() 中提供的迭代器不 保证以任何特定的顺序遍历优先级队列中的元素。如果需要按顺序遍历，请考虑使用 Arrays.sort(pq.toArray())。

注意，此实现不是同步的。如果多个线程中的任意线程修改了队列，则这些线程不应同时访问 PriorityQueue 实例。相反，请使用线程安全的 PriorityBlockingQueue 类。

**实现注意事项：** 
此实现为排队和出队方法（offer、poll、remove() 和 add）提供 O(log(n)) 时间； 
为 remove(Object) 和 contains(Object) 方法提供线性时间； 
为获取方法（peek、element 和 size）提供固定时间。

此类是 Java Collections Framework 的成员。 

| 返回值                | 方法               | 说明                                                         |
| --------------------- | ------------------ | ------------------------------------------------------------ |
| **boolean**           | **add(E e)**       | **将指定的元素插入此优先级队列。**                           |
| void                  | clear()            | 从此优先级队列中移除所有元素。                               |
| Comparator<? super E> | comparator()       | 返回用来对此队列中的元素进行排序的比较器；如果此队列根据其元素的自然顺序进行排序，则返回null。 |
| boolean               | contains(Object o) | 如果此队列包含指定的元素，则返回true。                       |
| Iterator<E>           | iterator()         | 返回在此队列中的元素上进行迭代的迭代器。                     |
| **boolean**           | **offer(E e)**     | **将指定的元素插入此优先级队列。**                           |
| **E**                 | **peek()**         | **获取但不移除此队列的头；如果此队列为空，则返回null。**     |
| **E**                 | **poll()**         | **获取并移除此队列的头，如果此队列为空，则返回null。**       |
| boolean               | remove(Object o)   | 从此队列中移除指定元素的单个实例（如果存在）。               |
| int                   | size()             | 返回此collection中的元素数。                                 |
| Object[]              | toArray()          | 返回一个包含此队列所有元素的数组。                           |
| <T> T[]               | toArray(T[] a)     | 返回一个包含此队列所有元素的数组；返回数组的运行时类型是指定数组的类型。 |

## 用法

#### 采用自然排序的方式

```java
import java.util.PriorityQueue;

public class PriorityQueueTest {
    private static PriorityQueue priorityQueue = new PriorityQueue();
    public static void main(String[] args) {
        priorityQueue.add(new Item(10));
        priorityQueue.add(new Item(20));
        priorityQueue.add(new Item(60));
        priorityQueue.add(new Item(30));
        priorityQueue.add(new Item(40));
        priorityQueue.add(new Item(50));

        while (true) {
            Object poll = priorityQueue.poll();
            if(poll != null){
                System.out.println(poll);
            }else{
                break;
            }
        }
    }
}

class Item implements Comparable<Item>{
    private int age;

    public Item(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Item{" +
                "age=" + age +
                '}';
    }

    @Override
    public int compareTo(Item o) {
        return this.age - o.age;//从小到大
    }
}

```

#### 采用指定比较器的方式

```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    private static PriorityQueue priorityQueue = new PriorityQueue(new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getAge() - o2.getAge();
        }
    });
    public static void main(String[] args) {
        priorityQueue.add(new Item(10));
        priorityQueue.add(new Item(20));
        priorityQueue.add(new Item(60));
        priorityQueue.add(new Item(30));
        priorityQueue.add(new Item(40));
        priorityQueue.add(new Item(50));

        while (true) {
            Object poll = priorityQueue.poll();
            if(poll != null){
                System.out.println(poll);
            }else{
                break;
            }
        }
    }
}

class Item{
    private int age;

    public Item(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Item{" +
                "age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


```

#### 执行结果，一致，均为

```
Item{age=10}
Item{age=20}
Item{age=30}
Item{age=40}
Item{age=50}
Item{age=60}
```

## 选择最大的K个数

用PriorityQueue默认是自然顺序排序，要选择最大的k个数，构造小顶堆，每次取数组中剩余数与堆顶的元素进行比较，如果新数比堆顶元素大，则删除堆顶元素，并添加这个新数到堆中。

Java中的PriorityQueue来实现堆，用PriorityQueue的实现的代码如下：

```java
public class findTopK {

    //找出前k个最大数，采用小顶堆实现
    public static int[] findKMax(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);//队列默认自然顺序排列，小顶堆，不必重写compare

        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (pq.peek() < num) {//如果堆顶元素 < 新数，则删除堆顶，加入新数入堆
                pq.poll();
                pq.offer(num);
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k&&!pq.isEmpty(); i++) {
            result[i] = pq.poll();
        }
        return result;
    }

 public static void main(String[] args) {
        int[]arr=new int[]{1, 6, 2, 3, 5, 4, 8, 7, 9};
        System.out.println(Arrays.toString(findKMax( arr,5)));
    }
}
/**
输出：[5, 6, 7, 8, 9]
*/
```

## 选择最小的K个数

选择最小的k个数可以用冒泡排序，复杂度为O(n*k)，有点高。 
要选择最小的K个数使用大顶堆，每次取数组中剩余数与堆顶的元素进行比较，如果新数比堆顶元素小，则删除堆顶元素，并添加这个新数到堆中。

Java中的PriorityQueue来实现堆，用PriorityQueue的实现的代码如下：

```java
public class findTopMinK {

    ////要找前k个最小数，则构建大顶堆，要重写compare方法
    public static int[] findKMin(int[] nums, int k) {

        PriorityQueue<Integer> pq =
                new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (pq.peek() > num) {//如果堆顶元素 > 新数，则删除堆顶，加入新数入堆
                pq.poll();
                pq.offer(num);
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k&&!pq.isEmpty(); i++) {
            result[i] = pq.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[]arr=new int[]{1, 6, 2, 3, 5, 4, 8, 7, 9};
        System.out.println(Arrays.toString(findKMin( arr,5)));
    }

}
/**
输出：[5, 4, 3, 2, 1]
*/
```

