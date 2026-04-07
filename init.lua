box.cfg{
    listen = '0.0.0.0:3301'
}
box.schema.user.grant('guest', 'read,write,execute', 'universe', nil, { if_not_exists = true })