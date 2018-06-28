# Generated by Django 2.0.2 on 2018-06-13 01:29

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('shop', '0007_auto_20180606_0808'),
    ]

    operations = [
        migrations.CreateModel(
            name='VotedUsersOnPost',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('mark', models.DecimalField(decimal_places=2, max_digits=10)),
                ('post', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='shop.Post')),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
    ]
