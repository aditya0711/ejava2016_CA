/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aditya Aggarwal
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"),
    @NamedQuery(name = "Posts.findByPostid", query = "SELECT p FROM Posts p WHERE p.postid = :postid"),
    @NamedQuery(name = "Posts.findByTitle", query = "SELECT p FROM Posts p WHERE p.title = :title"),
    @NamedQuery(name = "Posts.findByTimestamp", query = "SELECT p FROM Posts p WHERE p.timestamp = :timestamp"),
    @NamedQuery(name = "Posts.findByContent", query = "SELECT p FROM Posts p WHERE p.content = :content"),
    @NamedQuery(name = "Posts.findByCategory", query = "SELECT p FROM Posts p WHERE p.category = :category"),
    @NamedQuery(name = "Posts.findByAuthor", query = "SELECT p FROM Posts p WHERE p.author = :author")})
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "postid")
    private String postid;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "timestamp")
    @Temporal(TemporalType.DATE)
    private Date timestamp;
    @Basic(optional = false)
    @Size(min = 1, max = 500)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "author")
    private String author;

    public Posts() {
    }

    public Posts(String postid) {
        this.postid = postid;
    }

    public Posts(String postid, String title, Date timestamp, String content, String category, String author) {
        this.postid = postid;
        this.title = title;
        this.timestamp = timestamp;
        this.content = content;
        this.category = category;
        this.author = author;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public Posts createCopy(){
        
        Posts post = new Posts();
        
        post.setAuthor(author);
        post.setCategory(category);
        post.setContent(content);
        post.setPostid(postid);
        post.setTimestamp(timestamp);
        post.setTitle(title);
        
        return post;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postid != null ? postid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.postid == null && other.postid != null) || (this.postid != null && !this.postid.equals(other.postid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejava.ca2.model.Posts[ postid=" + postid + author + category + title + content + " ]";
    }
    
}
