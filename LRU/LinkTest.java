package LRU;

import java.util.HashMap;
import java.util.Map;

public class LinkTest {
    public static void main(String[] args) {
        LinkList<Integer> l = new LinkList<>();
        add(l,12);
        add(l,1);
        add(l,11);
        LinkList<Integer> reserve1 = reserve(l);
        while(reserve1.next!=null){
            System.out.println(reserve1.next.value);
            reserve1=reserve1.next;
        }
        LinkList<Integer> l1 = new LinkList<>();
        add(l1,2);
        add(l1,7);
        add(l1,11);
        LinkList<Integer> reserve = combine(l, l1);
        LinkList<Integer>[] mid = findMid(reserve);
        System.out.println("中间值是：");
        for(int i=0;i<mid.length;i++){
            System.out.println(mid[i].value);
        }
        System.out.println("---------");
        while(reserve.next!=null){
            System.out.println(reserve.next.value);
            reserve=reserve.next;
        }



    }

    /**
     * 查找中间节点操作，如果数目很多，可以采用建立索引的方式来加快查询的速度，或者采用跳表，跳表正是建立索引的策略
     * 跳表是采用抛硬币的方法来决定建立索引的高度，建立索引是用空间来换取时间的策略
     * 此次练习不建立索引，采用一般方法
     * @param linkList
     * @return
     */
    public  static  LinkList<Integer>[]  findMid(LinkList<Integer> linkList) {
        int count = 0;
        int mid = 0;
        LinkList<Integer> point = linkList;
        LinkList<Integer>[] linkLists = new LinkList[2];

        while (point.next != null) {
            count++;
            point = point.next;

        }
        point = linkList;
        if (count % 2 == 1) {
            mid = count / 2 + 1;
            while (mid > 0) {
                point = point.next;
                mid--;
            }
            linkLists[0] = point;

        } else {
            mid = count / 2;
            while (mid > 0) {
                point = point.next;
                mid--;
            }
            linkLists[0] = point;
            linkLists[1] = point.next;
        }


        return  linkLists;

    }

    /**
     * 链表反转，输入链表为a-b-c返回链表c-b-a
     *
     * @param l
     * @param linkList
     * @return
     */
    public  static LinkList<Integer> reserve( LinkList<Integer> linkList){
        LinkList<Integer> linkList1=new LinkList<>();
        LinkList<Integer> index = linkList.next;
        while(index!=null){
            LinkList<Integer> linkList2=new LinkList<>();
            linkList2.value=index.value;
            linkList2.next=linkList1.next;
            linkList1.next=linkList2;
            index=index.next;
        }
        return linkList1;

    }

    /**
     * 合并两个有序链表
     * @param linkList1
     * @param linkList2
     * @return
     */
    public static LinkList<Integer> combine(LinkList<Integer> linkList1,LinkList<Integer> linkList2){
        LinkList<Integer> combine = new LinkList<>();
        LinkList<Integer> point = combine;
        while(linkList1.next!=null&&linkList2.next!=null){
            LinkList<Integer> index = new LinkList<>();
            if(linkList1.next.value>linkList2.next.value){
                index.value=linkList2.next.value;
                index.next=point.next;
                point.next=index;
                linkList2=linkList2.next;
                point=point.next;
            }else{
                index.value=linkList1.next.value;
                index.next=point.next;
                point.next=index;

                linkList1=linkList1.next;
                point=point.next;
            }
        }
        if(linkList1.next==null&&linkList2.next!=null){
            while(linkList2.next!=null) {
                LinkList<Integer> index = new LinkList<>();
                index.value=linkList2.next.value;
                linkList2=linkList2.next;
                point.next=index;
                point=point.next;

            }

        }else if(linkList1.next!=null&&linkList2.next==null){
            while(linkList1.next!=null) {
                LinkList<Integer> index = new LinkList<>();
                index.value=linkList1.next.value;
                linkList1=linkList1.next;
                point.next=index;
                point=point.next;
            }
        }
        return combine;

    }

    /**
     * 链表顺序添加元素操作
     * @param root
     * @param t
     * @return
     */
    public static LinkList<Integer> add(LinkList<Integer> root,Integer t){

        LinkList<Integer> linkList = new LinkList<>();
        linkList.value=t;
        LinkList<Integer> q = root.next;
        LinkList<Integer> qq =root;
        if(root==null){//添加的是空链表
            linkList.next=root.next;
            root.next=linkList;
            return root;

        }
        while(q!=null&&q.value<=t){
            qq=q;
            q=q.next;


        }
        qq.next=linkList;
        linkList.next=q;
        return root;
    }

    /**
     * 单链表结构，头结点不保存元素
     * @param <T>
     */
    static class  LinkList<T>  {
        public T value;
        public LinkList<T> next;
        public LinkList() {
            next=null;
        }

    }
}
