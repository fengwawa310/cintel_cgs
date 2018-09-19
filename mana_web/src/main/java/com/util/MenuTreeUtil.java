package com.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.entity.sys.SysMenu;
import com.vo.sys.SysMenuVO;

public class MenuTreeUtil {
	   
	/**
	 * 建立树菜单
	 * @param menusDB 菜单集合（不是树）
	 * @return 有样式的树的html字符串
	 */
    public static String buildTreeHtml(List<SysMenu> menusDB){  
    	StringBuffer html = new StringBuffer();
        for (int i=0;i<menusDB.size();i++) { 
        	SysMenu node=menusDB.get(i);
            if ("0".equals(node.getParentId())) { 
            	boolean childrenHas=false;
            	List<SysMenu> children = getChildren(menusDB,node);  
            	if(!children.isEmpty())childrenHas=true;
            	if(i==0){
            		html.append("\r\n<li id='menu"+node.getId()+"' class='active' >"); 
            	}else{
            		html.append("\r\n<li id='menu"+node.getId()+"' >"); 
            	}           	
            	html.append("\r\n<a ");
            	html.append(" href='#maincontent' style='cursor:pointer;'  onclick=\"openMenu('1','menu"+node.getId()+"','menu0','"+node.getName()+"','"+node.getName()+"','"+(StringUtils.isNotBlank(node.getHref())?node.getHref().trim():"noset")+"','"+node.getTarget()+"')\" ");       		    	
            	if(childrenHas)html.append(" target='mainFrame' class='dropdown-toggle' "); 
            	html.append(" >"); 
            	if(StringUtils.isNotEmpty(node.getIcon()))html.append("\r\n<i class= "+ node.getIcon()+" ></i>"); 
            	html.append("\r\n<span class='menu-text'>" + node.getName()+ "</span>"); 
            	if(childrenHas)html.append("<b class='arrow icon-angle-down'></b>");
            	html.append("</a>");
                build(menusDB,node,html);  
                html.append("</li>");
            }  
        }  
        return html.toString();  
    }

	private static void build(List<SysMenu> menusDB,SysMenu node,StringBuffer html){
		List<SysMenu> children = getChildren(menusDB,node);
		if (!children.isEmpty()) {
			html.append("\r\n<ul class='submenu'>");
			for (SysMenu child : children) {
				boolean childrenHas=false;
				List<SysMenu> children2 = getChildren(menusDB,child);
				if(!children2.isEmpty())childrenHas=true;
				html.append("\r\n<li id='menu"+child.getId()+"' >");
				html.append("\r\n<a ");
				html.append(" href='#maincontent' style='cursor:pointer;' onclick=\"openMenu('1','menu"+child.getId()+"','menu"+child.getParentId()+"','"+child.getName()+"','"+node.getName()+">>"+child.getName()+"','"+(StringUtils.isNotBlank(child.getHref())?child.getHref().trim():"noset")+"','"+child.getTarget()+"')\" ");
				if(childrenHas)html.append(" target='mainFrame' class='dropdown-toggle' ");
				html.append(" >");
				if(!"0".equals(child.getParentId()))html.append("\r\n<i class='icon-double-angle-right' ></i>");
				html.append("\r\n<span class='menu-text'>");
				if(StringUtils.isNotEmpty(child.getIcon())){
					html.append("\r\n<i class= "+ child.getIcon()+" ></i>&nbsp;&nbsp;"+child.getName());
				}else{
					html.append(child.getName());
				}
				html.append("</span>");
				if(childrenHas)html.append("<b class='arrow icon-angle-down'></b>");
				html.append("</a>");
				build(menusDB,child,html);
				html.append("</li>");
			}
			html.append("\r\n</ul>");
		}
	}

	public static String buildTreeHtml02(List<SysMenu> menusDB){
		StringBuffer html = new StringBuffer();
		for (int i=0;i<menusDB.size();i++) {
			SysMenu node=menusDB.get(i);
			if ("0".equals(node.getParentId())) {
				boolean childrenHas=false;
				List<SysMenu> children = getChildren(menusDB,node);
				if(!children.isEmpty())childrenHas=true;
				String href = "'"+node.getHref()+"'";
				String firstMenuId = "'m"+node.getId()+"'";
				String current = "'"+node.getName()+"'";
//				if(i==0){
//					html.append("<li class=\"dropdown\">\n" +
//							"<a aria-expanded=\"false\" role=\"button\" href=\""+href+"\">"+node.getName()+"</a>\n");
//				}else
				if(!childrenHas){
					html.append("<li class=\"dropdown\" id=\"m"+node.getId()+"\">\n" +
							"<a aria-expanded=\"false\" role=\"button\" onclick=\"jumpPage("+href+","+firstMenuId+","+current+");\">" +
							"<span class=\""+node.getIcon()+" titleSpan\"></span>"
							+node.getName()+"</a>\n");
				}else{
					html.append("<li class=\"dropdown\" id=\"m"+node.getId()+"\">\n" +
							"<a aria-expanded=\"false\" role=\"button\" href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">" +
							"<span class=\""+node.getIcon()+" titleSpan\"></span>"
							+node.getName()
							+"</a>\n");
				}
				if (childrenHas) {
					build02(menusDB,node,html);
				}
				html.append("</li>");
			}
		}
		return html.toString();
	}

	private static void build02(List<SysMenu> menusDB,SysMenu node,StringBuffer html){
		String firstMenuId = "'m"+node.getId()+"'";
        List<SysMenu> children = getChildren(menusDB,node); 
        if (!children.isEmpty()) {
            html.append("<ul role=\"menu\" class=\"dropdown-menu\">");
            for (SysMenu child : children) {
				String href = "'"+child.getHref()+"'";
				String current = "'"+child.getName()+"'";
            	html.append("<li><a onclick=\"jumpPage("+href+","+firstMenuId+","+current+");\">"+child.getName()+"</a></li>");
            }
            html.append("</ul>");
        }   
    }  
      
    private static List<SysMenu> getChildren(List<SysMenu> menusDB,SysMenu node){  
        List<SysMenu> children = new ArrayList<SysMenu>();  
        String id = node.getId();  
        for (SysMenu child : menusDB) {  
            if (id.equals(child.getParentId())) {  
                children.add(child);               
            }  
        }  
        return children;  
    }  
    
	   
	/**
	 * 建立树菜单
	 * @param menusDB 菜单集合（不是树）
	 * @return 有样式的树的菜单集合
	 */
    public static List<SysMenu> buildTree(List<SysMenu> menusDB){  
    	List<SysMenu> res=new ArrayList<SysMenu>();
    	  for (SysMenu node:menusDB) { 
    		  if ("0".equals(node.getParentId())) { 
    			  List<SysMenu> children = getChildren(menusDB,node);  
//    			  node.setNodes(children);
    			  build(menusDB,node,res); 
    			  res.add(node);
    		  }    		
    	  }
    	return res;
    }  
    
    private static void build(List<SysMenu> menusDB,SysMenu node,List<SysMenu> res){  
        List<SysMenu> children = getChildren(menusDB,node); 
        if (!children.isEmpty()) {  
            for (SysMenu child : children) {  
            	List<SysMenu> children2 = getChildren(menusDB,child);
//            	child.setNodes(children2);
                build(menusDB,child,res);  
            } 
        }   
    } 
    
    
}
