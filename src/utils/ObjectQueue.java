package utils;

public class ObjectQueue {
    static public ObjectQueue Queue= new ObjectQueue();
    static public ObjectQueue QueueTrans= new ObjectQueue();

    public class ObjectBox{
        public int id;
        public FirstShape figure;
        public ObjectBox boxNext= null;
        public ObjectBox boxBack= null;
        public  int delet=0;

        public ObjectBox(int id, FirstShape figure){
            this.id= id;
            this.figure= figure;
        }
    }

    static public ObjectQueue mainQueue= new ObjectQueue();
    public ObjectBox object;

    public ObjectBox head= null;
    public ObjectBox tail= null;
    public int size= 0;
    public int index= 0;

    public ObjectQueue(){}

    public void add(FirstShape figure){
        figure.bind();
        ObjectBox obj= new ObjectBox(index, figure);
        if(head==null){
            head= obj;
            tail= obj;
        }else{
            tail.boxNext= obj;
            obj.boxBack= tail;
            tail= obj;
        }
        size++;
        index++;
    }

    public FirstShape focusedObject(){
        toHead();
        float minDistanse= 1000000;
        FirstShape nearFigure= null;
        while(object!=null){
            float dist= object.figure.pos.distFromCamera()+object.figure.size;
            if(minDistanse>dist && object.figure.planet==true && object.figure.focused==true) {
                minDistanse=dist;
                nearFigure= object.figure;
            }
            next();
        }
        return nearFigure;
    }

    public void sortByNear(){
        ObjectBox start= head;
        ObjectBox end= tail;

        object= start;
        for (int i=0;i<(size/2+size%2);i++) {
            object= start;
            float bigDist= start.figure.pos.distFromCamera();
            float smallDist= end.figure.pos.distFromCamera();
            ObjectBox bigBox= start;
            ObjectBox smallBox= end;
            while (object != end.boxNext) {
                if (object.figure.pos.distFromCamera()>bigDist){
                    bigDist=object.figure.pos.distFromCamera();
                    bigBox= object;
                }
                if (object.figure.pos.distFromCamera()<smallDist){
                    smallDist=object.figure.pos.distFromCamera();
                    smallBox= object;
                }
                next();
            }

            swap(start,bigBox);
            if(start== smallBox)
                swap(bigBox,end);
            else
                swap(end, smallBox);

            start= start.boxNext;
            end= end.boxBack;
            toHead();
        }
    }

    public void sortBySize(){
        ObjectBox start= head;
        ObjectBox end= tail;

        object= start;
        for (int i=0;i<(size/2+size%2);i++) {
            object= start;
            float bigSize= start.figure.size;
            float smallSize= end.figure.size;
            ObjectBox bigBox= start;
            ObjectBox smallBox= end;
            while (object != end.boxNext) {
                if (object.figure.size>bigSize){
                    bigSize=object.figure.size;
                    bigBox= object;
                }
                if (object.figure.size<smallSize){
                    smallSize=object.figure.size;
                    smallBox= object;
                }
                next();
            }

            swap(start,bigBox);
            if(start== smallBox)
                swap(bigBox,end);
            else
                swap(end, smallBox);

            start= start.boxNext;
            end= end.boxBack;
            toHead();
        }
    }

    public void swap(ObjectBox a,ObjectBox b){
        FirstShape swap;
        swap= a.figure;
        a.figure= b.figure;
        b.figure= swap;
    }

    public void delete(){
        if(object==head){
            head= object.boxNext;
            object.boxNext.boxBack= null;
        }else if(object==tail){
            object.boxBack.boxNext= null;
            tail= object.boxBack;
        }else{
            object.boxBack.boxNext= object.boxNext;
            object.boxNext.boxBack= object.boxBack;
        }
        System.runFinalization();
        size--;
    }


    public void toHead(){
        object= head;
    }

    public void next(){
        object= object.boxNext;
    }

}
