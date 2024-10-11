# Kotlin-Jdsl demo

[Kotlin-Jdsl](https://github.com/line/kotlin-jdsl)

# Quick Start

Start server, and Send GET request to `http://localhost:8080/`

GET request to `http://localhost:8080/post` will work

# Why this happens

There will be SQL statement in the console:

```sql
select p1_0.id,p1_0.priority,p1_0.title from post p1_0 order by (p1_0.priority+cast(? as integer)) desc offset ? rows fetch first ? rows only
```

Parameter binding happens in the order clause, but this parameter in the sql does not appear in count query.

This makes parameter binding fail, and the following exception appears:

```
org.hibernate.query.UnknownParameterException: No parameter named ':param1' in query with named parameters []
	at org.hibernate.query.internal.ParameterMetadataImpl.getQueryParameter(ParameterMetadataImpl.java:262) ~[hibernate-core-6.5.3.Final.jar:6.5.3.Final]
	at org.hibernate.query.spi.AbstractCommonQueryContract.setParameter(AbstractCommonQueryContract.java:826) ~[hibernate-core-6.5.3.Final.jar:6.5.3.Final]
	at org.hibernate.query.spi.AbstractSelectionQuery.setParameter(AbstractSelectionQuery.java:882) ~[hibernate-core-6.5.3.Final.jar:6.5.3.Final]
	at org.hibernate.query.sqm.internal.QuerySqmImpl.setParameter(QuerySqmImpl.java:1205) ~[hibernate-core-6.5.3.Final.jar:6.5.3.Final]
	at org.hibernate.query.sqm.internal.QuerySqmImpl.setParameter(QuerySqmImpl.java:136) ~[hibernate-core-6.5.3.Final.jar:6.5.3.Final]
	at com.linecorp.kotlinjdsl.support.spring.data.jpa.JpqlEntityManagerUtils.setParams(JpqlEntityManagerUtils.kt:101) ~[spring-data-jpa-support-3.5.2.jar:na]
	at com.linecorp.kotlinjdsl.support.spring.data.jpa.JpqlEntityManagerUtils.createQuery(JpqlEntityManagerUtils.kt:85) ~[spring-data-jpa-support-3.5.2.jar:na]
	at com.linecorp.kotlinjdsl.support.spring.data.jpa.JpqlEntityManagerUtils.access$createQuery(JpqlEntityManagerUtils.kt:14) ~[spring-data-jpa-support-3.5.2.jar:na]
	at com.linecorp.kotlinjdsl.support.spring.data.jpa.JpqlEntityManagerUtils$createEnhancedQuery$1.invoke(JpqlEntityManagerUtils.kt:74) ~[spring-data-jpa-support-3.5.2.jar:na]
	at com.linecorp.kotlinjdsl.support.spring.data.jpa.JpqlEntityManagerUtils$createEnhancedQuery$1.invoke(JpqlEntityManagerUtils.kt:72) ~[spring-data-jpa-support-3.5.2.jar:na]
	at com.linecorp.kotlinjdsl.support.spring.data.jpa.EnhancedTypedQuery$countQuery$2.invoke(EnhancedTypedQuery.kt:9) ~[spring-data-jpa-support-3.5.2.jar:na]
	at com.linecorp.kotlinjdsl.support.spring.data.jpa.EnhancedTypedQuery$countQuery$2.invoke(EnhancedTypedQuery.kt:9) ~[spring-data-jpa-support-3.5.2.jar:na]

    ...
```