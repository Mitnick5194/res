package com.ajie.res.JMX;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 该对象包含了对MBean的注册事件，如果需要将对象注册到JMX管理，则需要继承该类，并重写<br>
 * 两个抽象方法 getDomain() 和 getKeyAndName，直接在子类调用register/unregister即可注册/注销
 * 
 * @author niezhenjie
 */
public abstract class AbstractMBean implements MBean {
	private static final Log log = LogFactory.getLog(AbstractMBean.class);
	private MBeanServer server = null;

	public void register() {
		MBeanServer s = server;
		if (null == s) {
			s = ManagementFactory.getPlatformMBeanServer();
		}
		ObjectName keyAndName = getKeyAndName();
		StringBuilder sb = new StringBuilder(getDomain());
		sb.append(":");
		sb.append(keyAndName);
		ObjectName o = null;
		try {
			o = new ObjectName(sb.toString());
			s.registerMBean(this, o);
			log.info("已注册MBean: " + sb.toString());
		} catch (MalformedObjectNameException e) {
			log.warn("MBean注册失败: " + sb.toString() + " , " + e);
		} catch (InstanceAlreadyExistsException e) {
			log.warn("MBean注册失败: " + sb.toString() + " , " + e);
		} catch (MBeanRegistrationException e) {
			log.warn("MBean注册失败: " + sb.toString() + " , " + e);
		} catch (NotCompliantMBeanException e) {
			log.warn("MBean注册失败: " + sb.toString() + " , " + e);
		}
	}

	public void unregister() {
		MBeanServer s = server;
		if (null == s) {
			s = ManagementFactory.getPlatformMBeanServer();
		}
		ObjectName keyAndName = getKeyAndName();
		StringBuilder sb = new StringBuilder(getDomain());
		sb.append(":");
		sb.append(keyAndName);
		ObjectName o = null;
		try {
			o = new ObjectName(sb.toString());
			s.unregisterMBean(o);
			log.warn("注销MBean: " + sb.toString());
		} catch (MalformedObjectNameException e) {
			log.warn("MBean注销失败: " + sb.toString() + ", " + e);
		} catch (MBeanRegistrationException e) {
			log.warn("MBean注销失败: " + sb.toString() + ", " + e);
		} catch (InstanceNotFoundException e) {
			log.warn("MBean注销失败: " + sb.toString() + ", " + e);
		}
	}

	public abstract String getDomain();

	public abstract ObjectName getKeyAndName();
}
