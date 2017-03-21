package com.android.diy.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.bean.NodeBean;
import com.android.diy.app.databinding.ActivitySelectNodeBinding;
import com.android.diy.app.ui.adapter.NodeChildAdapter;
import com.android.diy.app.ui.adapter.NodeGroupAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cheng on 2017/3/7.
 * 选择节点
 */
public class SelectNodeActivity extends BaseActivity<ActivitySelectNodeBinding> {

    public static final int REQUEST_NODE = 11;
    public static final String NODE_LIST = "node_list";
    private ArrayList<NodeBean> mNodeList;
    private ArrayList<String> listGroup, listChild;
    private NodeGroupAdapter groupAdapter;
    private NodeChildAdapter childAdapter;
    private String groupName, childName = "";  // 父节点/子节点名称
    private int nodeId = 0;  // 节点编号

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_select_node;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mNodeList = this.getIntent().getParcelableArrayListExtra(NODE_LIST);
        initActionBar(mDataBinding.appToolbar.toolbar);
        listGroup = getSectionNames(mNodeList);

        groupAdapter = new NodeGroupAdapter(this);
        childAdapter = new NodeChildAdapter(this);

        groupAdapter.addAll(listGroup);
        mDataBinding.listGroup.setAdapter(groupAdapter);

        mDataBinding.listGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                groupAdapter.setSelectItem(position);
                groupName = listGroup.get(position);
                nodeId = mNodeList.get(position).getSectionId();
                listChild = getNodeNames(mNodeList, groupName);
                childAdapter.clear();
                childAdapter.addAll(listChild);
                mDataBinding.listChild.setAdapter(childAdapter);
            }
        });
        mDataBinding.listChild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                childAdapter.setSelectItem(position);
                childName = listChild.get(position);
            }
        });
        mDataBinding.setCompleteAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(ReleaseTopicActivity.GROUP_NAME, groupName);
                intent.putExtra(ReleaseTopicActivity.CHILD_NAME, childName);
                intent.putExtra(ReleaseTopicActivity.NODE_ID, nodeId);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private ArrayList<String> getSectionNames(ArrayList<NodeBean> nodeList) {
        ArrayList<String> parents = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (NodeBean node : nodeList) {
            String element = node.getSectionName();
            if (set.add(element)) parents.add(element);
        }
        return parents;
    }

    private ArrayList<String> getNodeNames(ArrayList<NodeBean> nodeList, String sectionName) {
        ArrayList<String> nodeNameList = new ArrayList<>();
        for (NodeBean node : nodeList) {
            String element = node.getSectionName();
            if (element.equals(sectionName)) {
                nodeNameList.add(node.getName());
            }
        }
        return nodeNameList;
    }
}
