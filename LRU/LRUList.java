package LRU;


import java.util.Iterator;
import java.util.LinkedList;

public class LRUList  {
    //最大缓存数
    private final int MAX_SIZE=2;
    //链表缓存结构
    private LinkList<User> LRU;//头结点不存储值
    //当前缓存个数
    private int count;

     public LRUList(){
         LRU= new LinkList<>();
         System.out.println("创建缓存链表成功！");
    }
    public User findFromCache(String value){
         LinkList<User> nodeq;
               nodeq=  LRU.next;
        LinkList<User> nodeh;
              nodeh=  LRU;
         int count=0;
         while(null!=nodeq&&nodeq.value.getName()!=value){//遍历查找
            nodeh=nodeq;
             nodeq =nodeq.next;

         count++;
         }
         if(null==nodeq){//如果没有找到
             System.out.println("缓存中没有查到数据，将从数据库中查找");
             if(count>=this.MAX_SIZE){//缓存满了
                 nodeh=null;//删除最后的节点
                 LinkList<User> linkList = new LinkList<>();
                 System.out.println("缓存数据已满，进行清除！");
                 linkList.value= new User("存满了添加缓存",1,1);
                 linkList.next=LRU.next;
                 LRU.next=linkList;
                 show();
             return linkList.value;
             }else{//没有存满
                 System.out.println("缓存没有满！");
                 LinkList<User> linkList = new LinkList<>();
                 linkList.value= new User("没存满添加缓存",1,1);
                 nodeh.next=linkList;
                 this.count=count+1;//更新存储个数
                show();
                 return linkList.value;

             }
         }else{//找到了
             System.out.println("缓冲中存在数据");
             return nodeq.value;
         }
    }
    public LinkList<User> getCache(){//获得全部缓存数据
         return LRU;
    }

    public void show(){
        LinkList<User> linkList1 ;
        linkList1=LRU.next;
        System.out.println("---------------------");
        System.out.println("缓存中数据是：");
        while(linkList1!=null){
            System.out.println(linkList1.value.getName());
            linkList1=linkList1.next;
        }
        System.out.println("---------------------");
    }


    /**
     * 单链表结构
     * @param <T>
     */
    class  LinkList<T>  {
         public T value;
         public LinkList<T> next;
        public LinkList() {
            next=null;
        }



    }
}
