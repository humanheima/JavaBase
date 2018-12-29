package com.hm.structure;

/**
 * Created by dmw on 2018/12/28.
 * Desc:
 */
public class Temp {

    /**
     * 插入节点后的修复操作
     *
     * @param node 新插入的节点
     */
    private void insertFixUp(RBNode<T> node) {
        RBNode<T> parent, gparent; //定义父节点和祖父节点

        //需要修复的条件：父节点存在，且父节点的颜色是红色
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);//获得祖父节点

            //若父节点是祖父节点的左子节点
            if (parent == gparent.left) {
                RBNode<T> uncle = gparent.right; //获得叔叔节点

                //case1: 叔叔节点也是红色
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent); //把父节点和叔叔节点涂黑
                    setBlack(uncle);
                    setRed(gparent); //把祖父节点涂红
                    node = gparent; //将位置放到祖父节点处
                    continue; //继续while，重新判断
                }
                //...省略
            } else { //若父节点是祖父节点的右子节点，与上面的完全相反，本质一样

                //... 省略
            }
        }
        //将根节点设置为黑色
        setBlack(this.root);

    }
}
