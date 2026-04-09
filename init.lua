box.cfg{}

if box.space.data_entity == nil then
    local s = box.schema.space.create('KV')
    s:format({
        { name = 'key',   type = 'string' },
        { name = 'value', type = 'varbinary' },
    })
    s:create_index('primary', {
        type  = 'hash',
        parts = { 'key' }
    })
    print('Space data_entity created')
end

box.schema.user.grant('guest', 'read,write,execute', 'universe', nil, { if_not_exists = true })