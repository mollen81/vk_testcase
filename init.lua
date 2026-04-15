if box.space.KV == nil then
    local s = box.schema.space.create('KV')
    s:format({
        { name = 'key',   type = 'string' },
        { name = 'value', type = 'varbinary' },
    })
    s:create_index('primary', {
        type  = 'hash',
        parts = { 'key' }
    })
    print('Space KV created')
end